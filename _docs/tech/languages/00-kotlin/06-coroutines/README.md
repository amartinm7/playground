# Coroutines

Coroutines are the way of working or mechanism to handle the performance of the  I/O tasks.

suspend functions are functions which are suspendable (waitable or stoppable), but themselves are not the trigger to do async or I/O tasks or to the stop itself. I mean, the coroutine is the trigger to the magic to do enable the suspendable feature.

When you have an usecase, controller or similar, you can create a coroutine to use the async I/O tasks. 

To create a coroutine you need a GlobalScope or runBlocking... 

```kotlin
GlobalScope.launch { // when you do this you are creating a coroutine, if the IDE tell you coroutineScope, you are creating a coroutine
// your async-suspendable code goes here
}

runBlocking { 
// this: CoroutineScope
}

```

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

