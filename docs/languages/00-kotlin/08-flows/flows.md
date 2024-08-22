# Flows

Flow can handle the `backpressure`

Kotlin Flow is a reactive streams library introduced in Kotlin 1.3 that provides a declarative and composable way to work with asynchronous data streams. It is designed to handle asynchronous and potentially infinite sequences of values, allowing developers to write reactive, non-blocking, and asynchronous code in a more concise and intuitive manner.

Key Concepts of Kotlin Flow:
- Asynchronous Streams: Kotlin Flow models asynchronous data streams that emit multiple values over time. It can represent both finite and infinite sequences of values.
Suspending Functions: Flow is built on top of suspending functions, which are functions that can be paused and resumed. This allows developers to write non-blocking code without using callbacks or explicit threading.
- Cold Streams: Flow is a cold stream, which means that the data stream is only activated when a terminal operator is applied. It starts emitting values when a collector is connected to it.
- Flow Builders: Kotlin Flow provides various flow builders to create data streams, including flowOf, asFlow, channelFlow, and more. These builders allow you to create flows from different data sources or define custom flow behavior.
- Operators and Transformations: Flow provides a rich set of operators and transformations that can be applied to manipulate and transform the emitted values. Some commonly used operators include map, filter, flatMap, zip, merge, and more.
- Flow Context: Flow maintains the context preservation property, which means that the context in which the flow was created is propagated to downstream operators. This allows for seamless context switching and integration with coroutine features such as withContext and coroutineScope.
- Flow Cancellation: Flow supports cancellation, allowing you to cancel the data stream at any point. This helps in managing resources and stopping unnecessary computation when it's no longer needed.
- Error Handling: Flow provides error handling mechanisms, such as catch and onEach, to handle exceptions that occur during the flow emission or processing. It allows you to gracefully handle errors and continue the flow execution.
- Integration with Coroutines: Kotlin Flow is tightly integrated with coroutines, allowing you to seamlessly combine flows with other coroutine features like async, await, and withTimeout. This integration provides a unified programming model for both synchronous and asynchronous code.

How do we combine emissions from multiple Flows? Coroutines provide three operators to do it, namely `combine`, `zip`, and `flattenMerge`. Each operator has their own purpose of usage.

## combine flows


```kotlin
Flow<T1>.combine(
    flow: Flow<T2>,
    transform: suspend (a: T1, b: T2) -> R
): Flow<R> (source)
```

We have two flows, one is emitting numbers 1, 2, 3, and the other is emitting letters A, B, C, with a delay of 1000 and 2000 milliseconds respectively on each emission

```kotlin
val numbersFlow = flowOf(1, 2, 3).delayEach(1000)
val lettersFlow = flowOf("A", "B", "C").delayEach(2000)
```

combining

```kotlin
@ExperimentalCoroutinesApi
fun flowsWithCombine() = runBlocking {
    val numbersFlow = flowOf(1, 2, 3).delayEach(1000)
    val lettersFlow = flowOf("A", "B", "C").delayEach(2000)
    numbersFlow.combine(lettersFlow) { number, letter ->
        "$number$letter"
    }.collect {
        println(it)
    }
}
```