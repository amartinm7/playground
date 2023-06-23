# Concurrency vs paralelism 

## Concurrent vs parallel

A system is said to be `concurrent` if it can support two or more actions `in progress` at the same time. 
A system is said to be `parallel` if it can support two or more actions executing `simultaneously`.

![concurrencyvsparrallism2.jpg](_img%2Fconcurrencyvsparrallism2.jpg)

`concurrent` programming is using `only a core of the CPU` and executing several tasks during the same period time. 
The tasks are in progress, but the scheduler is changing between doing a task during the time. 
So the effect is looks like the tasks are doing at the same time, but it's not, are doing at the same time 
because are in progress, but everytime only one task is executing in the `core of the CPU`. 

For instance: you have to eat the whole cake and you have to sing a song meanwhile. 
You can't do the both task at the same time, or eat or sing, but you can bite a bit of cake and sing a little, 
a keep on doing the same during the time until you have done the cake, and you have done to sing a song. 
The tasks have a duration, an you can alternate the execution between them. `This is concurrency`

But what's happened is you have `two cores of the CPU`, you can execute the task of eating a cake and the task of singing 
at the same time. So this is `parallelism`.

In the earlier example, you can execute `at the same time` the eating task in a core, 
and the singing task in the other `at the same time`.

![concurrencyvsparrallism.jpeg](_img%2Fconcurrencyvsparrallism.jpeg)

Another example of `parallelism` is when you split a process in a set of tasks and every task is executed at the same time 
in different `cores of the CPU`.

## Throughput

number of http requests or number I/O request per second.
The amount of information you can process per second or per minute...

## ExecutorService pattern

The executorService piece of code which is using a threadpool and queue to store the messages.
You can create a task and send it to the executor. 
The executor has an event loop to execute the task in a concurrent way.

![executor_service.jpg](_img%2Fexecutor_service.jpg)

## Sync vs Async, block vs unblocked

Mainly I/O has the problem of the latency and the throughput because they are blocking the resources until there's a response.

So your CPU is waiting without doing anything during a period of time.

We have to do all the I/O tasks async to increase the performance and reduce the latency.

blocking I/O request:

```kotlin
HTTPClient client = HTTPClient)()
String response = client.get("http:/ /www.mydata.com/data")
```

Synchronous = Blocking
A synchronous code is always blocking. It will slow down vour application if it blocks it for a long time.

Asynchronous
The code you write will be executed at some point in the future.

Asynchronous + Concurrent
Running a blocking code in another thread is a way to avoid blocking the main thread of your application.
How can you get the result from this other thread?

unblocking I/O request:

![executor_service.jpg](_img%2Fexecutor_service.jpg)

## Concurrent programming

![concurrent-topics.jpg](_img%2Fconcurrent-topics.jpg)

## Sync Callable/Supplier functions

In this example you can use `Callable` functions.
`Callables` or `suppliers` are executable tasks.
`Callables` or `suppliers` are like lambdas in kotlin.

![supplier.jpg](_img%2Fsupplier.jpg)

Define 3 callable functions

![sync-callable.jpg](_img%2Fsync-callable.jpg)

Create a list of callables

![sync-callable-execute.jpg](_img%2Fsync-callable-execute.jpg)

Over the list, iterate using a stream, and over the stream do a map to 
execute the callable.

Everything is `synchronous`

## ExecutorService, async

```kotlin
var executor: ExecutorService = Executors.newFixedThreadPool (4);
```

Over the `callable functions` in earlier example, we can `submit` the task to the `executorService`.
To do that we can use the submit method.
Over every submit execution, you get a `future` of the callable task.
A `Future` is a promise. 
So to get the result you have to do it one by one.
Using the `Future.get()` the future is resolved or get an error.
To resolve the Future we can use `get` or `join`.
It's better join, because doesn't throw any exception.

Finally, at the end of the program, the `executor has to be `shutdown`

![executor_futures.jpg](_img%2Fexecutor_futures.jpg)


## CompletableFuture async

Completable Future is another way to do async requests.

The completableFuture implementation implements Future and CompletionStage

```kotlin
class CompletableFuture<T>: Future<T>, CompletionStage<T>
```
![async-tips.jpg](_img%2Fasync-tips.jpg)

Over the earlier example, we can iterate over the list of suppliers, 
and for everyone, we can add it to the `CompletableFuture.supplierAsync()` 
to create a new instance of `CompletableFuture`.
To resolve the completeFuture we can use `get` or `join`. 
It's better join, because doesn't throw any exception.

![completableFuture-supplierasync.jpg](_img%2FcompletableFuture-supplierasync.jpg)

![completableFuture-join.jpg](_img%2FcompletableFuture-join.jpg)

![functions.jpg](_img%2Ffunctions.jpg)

### Chain of Completable futures

![async-tips-2.jpg](_img%2Fasync-tips-2.jpg)

Example with executor

![completableFuture-chained.jpg](_img%2FcompletableFuture-chained.jpg)

![completableFuture-code.jpg](_img%2FcompletableFuture-code.jpg)

Example with completableFutures

![completableFutures-chained.jpg](_img%2FcompletableFutures-chained.jpg)

## Conclusions
![completableFutures.jpg](_img%2FcompletableFutures.jpg)