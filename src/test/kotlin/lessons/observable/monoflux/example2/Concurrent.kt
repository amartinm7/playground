package lessons.observable.monoflux.example2

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.util.concurrent.CompletableFuture

class Concurrent {

    fun execute(list: List<Person>): Unit {
        val source = Flux.just(list)
            .doOnNext {
                items -> items.forEach{ item -> log.debug(item.toString())}
            }
            .subscribeOn(Schedulers.single())
            .subscribe()
    }

    companion object {
        val log = LoggerFactory.getLogger(Concurrent::class.java)
    }
}

sealed class Person(open val id: Int, val name: String, val age: Int) {
    data class NormalPerson(override val id: Int) : Person(id = 1, name = "Normal", age = 25)
    data class SimplePerson(override val id: Int) : Person(id = 2, name = "Simple", age = 24)
}

fun main() {
    Concurrent().execute(
        listOf(
            Person.NormalPerson(1),
            Person.SimplePerson(2),
            Person.NormalPerson(3)
        )
    )

    CompletableFuture::class.java

}
