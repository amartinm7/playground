# Reactive or Coroutines

In detail, the used frameworks and their corresponding versions are:

- Kotlin 1.4.32
- Kotlinx Coroutines 1.4.2
- Spring Boot + WebFlux 2.4.2
- Project Reactor 3.4.2
- R2DBC 0.8.4.RELEASE
- Spring Data R2DBC 1.2.3


Example invocation:

```bash
curl -s "http://localhost:8080/reactive/characters?lastName=Stark"
[{"id":1,"firstName":"Eddard","lastName":"Stark","house":1},{"id":6,"firstName":"Arya","lastName":"Stark","house":1}]
```

## with Reactive flux and mono

In reactive world, nothing would happen, according to the principle: Nothing happens until you subscribe.

```kotlin
@RestController
@RequestMapping("reactive/characters")
class ReactiveCharacterController(private val reactiveCharacterService: ReactiveCharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Flux<Character> =
        reactiveCharacterService.findByLastName(lastName)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): Mono<Character> =
        reactiveCharacterService.findById(id)

    @PutMapping
    fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): Mono<ResponseEntity<String>> =
        reactiveCharacterService
            .addCharacter(firstName, lastName)
            .map { ResponseEntity.status(HttpStatus.CREATED).build<String>() }
            .switchIfEmpty { Mono.just(ResponseEntity.ok().build()) }
            .onErrorResume(CharacterNotFoundException::class.java) {
                Mono.just(ResponseEntity.badRequest().body(it.message))
            }

    @DeleteMapping
    fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String): Mono<Void> =
        reactiveCharacterService.deleteByName(firstName, lastName)

}

@Service
class ReactiveCharacterService(private val reactiveHouseService: ReactiveHouseService,
                               private val reactiveCharacterRepository: ReactiveCharacterRepository) {

    fun findByLastName(lastName: String): Flux<Character> =
        reactiveCharacterRepository.findByLastName(lastName)

    fun findById(id: Long): Mono<Character> =
        reactiveCharacterRepository.findById(id)

    fun deleteByName(firstName: String, lastName: String): Mono<Void> =
        reactiveCharacterRepository
            .findByFirstNameAndLastName(firstName, lastName)
            .flatMap { reactiveCharacterRepository.deleteById(it.id!!) }

    fun addCharacter(firstName: String, lastName: String): Mono<Character> =
        reactiveCharacterRepository
            .existsByFirstNameAndLastName(firstName, lastName)
            .filter { it == false }
            .flatMap {
                reactiveHouseService
                    .findByName(lastName)
                    .switchIfEmpty { Mono.error { CharacterNotFoundException("No valid house found for the character $firstName $lastName!") }}
            }.flatMap { reactiveCharacterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
}

interface ReactiveCharacterRepository : ReactiveCrudRepository<Character, Long> {

    fun findByLastName(lastName: String): Flux<Character>
    fun findByFirstNameAndLastName(firstName: String, lastName: String): Mono<Character>
    fun existsByFirstNameAndLastName(firstName: String, lastName: String): Mono<Boolean>

}
```
## with coroutines

```kotlin
@RestController
@RequestMapping("coroutines/characters")
class CoroutinesCharacterController(private val coroutinesCharacterService: CoroutinesCharacterService) {

    @GetMapping
    fun findByName(@RequestParam lastName: String): Flow<Character> =
        coroutinesCharacterService.findByLastName(lastName)

    @GetMapping("/{id}")
    suspend fun findById(@PathVariable id: Long): Character? =
        coroutinesCharacterService.findById(id)

    @PutMapping
    suspend fun addCharacter(@RequestParam firstName: String, @RequestParam lastName: String): ResponseEntity<String> {
        return try {
            val character = coroutinesCharacterService.addCharacter(firstName, lastName)

            character?.let {
                ResponseEntity.status(HttpStatus.CREATED).build()
            } ?: ResponseEntity.ok().build()
        } catch (ex: CharacterNotFoundException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping
    suspend fun deleteByName(@RequestParam firstName: String, @RequestParam lastName: String) =
        coroutinesCharacterService.deleteByName(firstName, lastName)

}

@Service
class CoroutinesCharacterService(private val coroutinesHouseService: CoroutinesHouseService,
                                 private val coroutinesCharacterRepository: CoroutinesCharacterRepository) {

    fun findByLastName(lastName: String): Flow<Character> =
        coroutinesCharacterRepository.findByLastName(lastName)

    suspend fun findById(id: Long): Character? =
        coroutinesCharacterRepository.findById(id)

    suspend fun deleteByName(firstName: String, lastName: String) =
        coroutinesCharacterRepository
            .findByFirstNameAndLastName(firstName, lastName)
            ?.let {
                coroutinesCharacterRepository.deleteById(it.id!!)
            }

    suspend fun addCharacter(firstName: String, lastName: String): Character? =
        if (coroutinesCharacterRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            null
        } else {
            coroutinesHouseService.findByName(lastName)
                ?.let { coroutinesCharacterRepository.save(Character(firstName = firstName, lastName = lastName, house = it.id!!)) }
                ?: throw CharacterNotFoundException("No valid house found for the character $firstName $lastName!")
        }
}

interface CoroutinesCharacterRepository : CoroutineCrudRepository<Character, Long> {

    fun findByLastName(lastName: String): Flow<Character>
    suspend fun findByFirstNameAndLastName(firstName: String, lastName: String): Character?
    suspend fun existsByFirstNameAndLastName(firstName: String, lastName: String): Boolean

}
```

## Equivalence

The first thing to notice are the different return types, as we’re not using the reactive types anymore. In general, the following mapping can be applied:

```
Mono<T> → T?
Flux<T> → Flow<T>
Mono<Void> → Void?
```

After that, add the `suspends` to the functions no `flowables` (equivalence with Flux) , only that are T o Void? (equivalence with Mono).

The reason is that which is calling to the return `flowables` functions are `suspends` too. And it's no needed, it's redundant.


## Another example: Can we run suspending functions in parallel? Absolutely! 

```kotlin
@GetMapping("/{id}/stock")
suspend fun findOneInStock(@PathVariable id: Int): ProductStockView = coroutineScope {
    val product: Deferred<Product?> = async(start = CoroutineStart.LAZY) {
        productRepository.getProductById(id)
    }
    val quantity: Deferred<Int> = async(start = CoroutineStart.LAZY) {
        webClient.get()
          .uri("/stock-service/product/$id/quantity")
          .accept(APPLICATION_JSON)
          .retrieve().awaitBody<Int>()
    }
    ProductStockView(product.await()!!, quantity.await())
}
```

Here, by wrapping a suspending function in the async{} block, we get an object of the Deferred<> type. By default, coroutines are immediately scheduled for execution. As a result, if we want to run them exactly when the await() method is called, we need to pass CoroutineStart.LAZY as the optional start parameter.

Finally, to start executing functions, we call the await() method. In that case, the two functions will execute in parallel. This technique is also known as parallel decomposition.

It’s interesting to note that functions in async blocks are dispatched to separate worker threads. After that, actual I/O operations happen on threads from the Reactor NIO pool.

To enforce structured concurrency, we’ve used the coroutineScope{} scoping function to create our own scope. It will wait for all the coroutines inside the block to complete before completing itself. However, the coroutineScope{} function doesn’t block the current thread compared to runBlocking.

