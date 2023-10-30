# Reactive Programming RxJava, RxKotlin

Understand how reactive works with an example: 

- imagine we have a security camera recording into a room
- if there is any movement, the camara is recording the same image everytime.
- The recording is a constant `Flow` of recorded images, it's a sequence.
- so this images flow has been processed all the time. There is a work`flow` of processing images.
- if the image is the same as previous images, the alert is idle in the work`flow` of processing images
- but when the image is changing from the previous ones, the alert is executed in the work`flow` of processing images.
So the program `reacts` when something is changing.

So the main ideas here is: 
- `Flow` or `sequence` of <anything> to process 
- we have to process the sequence or workflow, so over the sequence we can iterate to do something (sequence.map(something())) 
and over the sequence we get another sequence from the previous ones, and we can apply another function or transformation. 
- You don't know the neither init or the end of the `flow`, only open the `channel` and `consume` the items... 
- The program can `react` and `emit` a `signal` when something has happened in the item of the flow. This is the `reactive` part.
So to handle the reactivity, we can create `actors` or `subscribers` to do whatever is needed.

## Observable or Mono/FLux

Observable/Mono/Flux is a class which behaviour is listen/iterate over a Stream or Collection de objects. 

In this example, create an Observable, and for item in the array, emits an event.
The subscriber is listening for every event, and reacts over it. 
So over item, print a console line.

```kotlin
// Get a reference of the Observable object
val observable = rx.Observable.from(listOf("hello", "world", "from", "here"))

// apply an action to every item in the previous string array.
observable.subscribe( Action1<String>() {
    fun call(item: String) {
        println(it)
    }
})

// Another example with lambda
val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).take(20)
val observable2 = Observable.interval(200, TimeUnit.MILLISECONDS).take(20)

Observable.merge(observable1, observable2).subscribe { item: Long ->
    println(it)
    Thread.leep(20000);
} 
```

![RxJavaSubscriptor.png](_img%2FRxJavaSubscriptor.png)


## Example 1

before starting import the next dependencies 

- implementation("org.springframework.boot:spring-boot-starter-webflux") // MONO AND FLUX from Reactor 
- implementation("io.reactivex.rxjava3:rxkotlin:3.0.1") // Observable from RxJava/RxKotlin

They two projects to do the same, but we are going to do the same example in both

the idea behind:

- Create an Stream
- Subscribe to the Stream to react to the items into the stream

## monads

Mono, Flux or Observable are `monads`, so they encapsulate a subject (Subject Under the Test when is testing)

## Example of mono inside a mono
## when we are mapping, but inside the logic we are calling to a method which is returning another mono. 
```kotlin
// flux fromIterable it's the same than just
Flux.fromIterable (personas)
.flatMap(p -> {
    p. setEdad (p. getEdad() + 10);
    return Mono.just (p);
}).subscribe (p -> Log.info(p.toString()));
```