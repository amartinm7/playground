# Coroutines

Coroutines are the way of working or mechanism to handle the performance of the  I/O tasks.

suspend functions are functions which are suspendable (waitable or stoppable), but themselves are not the trigger to do async or I/O tasks or to the stop itself. I mean, the coroutine is the trigger to the magic to do enable the suspendable feature.

When you have an usecase, controller or similar, you can create a coroutine to use the async I/O tasks. 

To start a new coroutine, use one of the main coroutine builders: launch, async, or runBlocking. Different libraries can define additional coroutine builders.

```kotlin
GlobalScope.launch { // when you do this you are creating a coroutine, if the IDE tell you coroutineScope, you are creating a coroutine
// your async-suspendable code goes here
}

runBlocking { 
// this: CoroutineScope
}

```
## Create coroutines

`async` starts a new coroutine and returns a `Deferred` object. `Deferred` represents a concept known by other names such as `Future` or `Promise`. It stores a computation, but it defers the moment you get the final result; it promises the result sometime in the future.

The main difference between `async` and `launch` is that launch is used to start a computation that isn't expected to return a specific result. launch returns a `Job` that represents the `coroutine`. It is possible to wait until it completes by calling `Job.join()`.

`Deferred` is a generic type that extends `Job`. An `async` call can return a Deferred<Int> or a Deferred<CustomType>, depending on what the lambda returns (the last expression inside the lambda is the result).

To get the result of a coroutine, you can call `await() on the Deferred instance. While waiting for the result, the coroutine that this await() is called from is suspended:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferred: Deferred<Int> = async {
        loadData()
    }
    println("waiting...")
    println(deferred.await())
}

suspend fun loadData(): Int {
    println("loading...")
    delay(1000L)
    println("loaded!")
    return 42
}
```

`runBlocking is used as a bridge between regular and suspending functions, or between the blocking and non-blocking worlds. It works as an adaptor for starting the top-level main coroutine. It is intended primarily to be used in main() functions and tests.

## how is working

The program:
- creates a main coroutine
- creates a second T1 coroutine, on the thread-pool
- the program can continue or into the main thread or into the async T1 thread.
- the log trace waiting or loading can be written in any order at this moment because the thread-pool distpacher can alternate the main and T1 execution and suspends the functions meanwhile
- finally to print the final log into the main program, we have to resolve the deferred promise, so the main gives the execution power to the T1 coroutine and waits until the execution returns the 42 value to print it. 
- After that the coroutine is finished and the main finish too.

```kotlin
fun main () = runBlocking { // creates a 'main' coroutine
    val deferred = async (Dispatchers.Default) { // creates a 'T1' coroutine
        loadData()
    } 
    println("waiting...")
    println(deferred.await()) // blocking call
}

suspend fun loadData(): Int {
    println("loading...")
    delay(1000L) // http call for instance
    println("loaded!")
    return 42
}
```

![coroutines-suspends-work.jpg](_img%2Fcoroutines-suspends-work.jpg)

[video](https://www.youtube.com/watch?v=zEZc5AmHQhk)

## coroutines and channels

- the program creates three different coroutines.
- there are two coroutines to produce and one for consume
- the communication occurrs using the channel.
- the producer sends a message throw the channel and the consumer receives the message. This process is one by one. 
- So when the producer goes to send the message to the channel, it has to wait its turn. I mean, it's there's not any producer before, it can send the message. 
- As we can see the first producer send the first message and waits or suspends the execution, because the channel is busy, and because the producer is waiting to send its message too. So when the producer A sends the message the producer B, which is suspended, can start another time and sends the B message. 
- The dispatcher alternates the execution between the producers. So the dispatcher can suspends the producers in any moment. The execution of the messages can be random and the logs too. For this reason the producer A in this example, starts first and ends first.

Take into account that meanwhile the channel is busy, it's not possible to send messages, the producers are blocked, suspended.

![coroutines-and-channels-1.jpg](_img%2Fcoroutines-and-channels-1.jpg)!

![coroutines-channel.jpg](_img%2Fcoroutines-channel.jpg)

![coroutines-and-channels-2.jpg](_img%2Fcoroutines-and-channels-2.jpg)!

![coroutines-and-channels-3.jpg](_img%2Fcoroutines-and-channels-3.jpg)!

[video](https://www.youtube.com/watch?v=HpWQUoVURWQ)

## More examples

example:
```kotlin
fun main() = runBlocking { // this: CoroutineScope
    launch { doWorld() }
    println("Hello")
}

// this is your first suspending function, but this is not a coroutine, the caller is the coroutine
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
```

another example `Concurrently executes both sections`
```kotlin
// Sequentially executes doWorld followed by "Done"
fun main() = runBlocking {
            doWorld()
            println("Done")
        }

// Concurrently executes both sections
suspend fun doWorld() = coroutineScope { // this: CoroutineScope
    // It creates a coroutine scope and does not complete 
    // until all launched children complete.
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}
```

