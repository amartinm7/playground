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

- divide your processing in small I/O operations and chain them!

Example with executor

![completableFuture-chained.jpg](_img%2FcompletableFuture-chained.jpg)

![completableFuture-code.jpg](_img%2FcompletableFuture-code.jpg)

Example with completableFutures

![completableFutures-chained.jpg](_img%2FcompletableFutures-chained.jpg)

Another example, the outcome of one future is the another future to pass to the 
next future.

`Reminder: when you call a java method all the arguments are evaluated and resolved if they are Futures`
`Reminder: use composition over combination when we were using futures (thenCompose() over thenCombine())`

```java
// chained futures
CompletableFuture<Quotation> quotationCF = 
        CompletableFuture.supplyAsync( ( ) -> getQuotation ( )) ;
CompletableFuture<EmailInfos> infosCF = 
        quotationCF .thenApply (quotation -> email(quotation));
CompletableFuture<Boolean> doneCF = 
        infosCF.thenApply (emailInfos -> writeToDB(emailInfos));

doneCF .thenApply (done -> updateGUI (done)) ;
// to execute the futures, and block the main thread
doneCF.join()
// to get the results
Object results = doneCF.get()
```

## Run three task in parallel, and get the first one in being ready: `any0f`

You can run three task, and `anyOf` get the faster completed, but the result can change 
in every execution. If the three task are completed at the same time, is returned one, 
but if you run the same code another time, you can get another which is done. 
Take care of this.

```java
Supplier<Weather> w1 = () -> getWeatherA();
Supplier<Weather> w2 = () -> getWeatherB();
Supplier<Weather> w3 = () -> getWeatherC();

// create the completableFutures
CompletableFuture<Weather> cf1 = CompletableFuture.supplyAsync(w1);
CompletableFuture<Weather> cf3 = CompletableFuture.supplyAsync(w1);
CompletableFuture<Weather> cf4 = CompletableFuture.supplyAsync(w1);

// pass the completeFutures
// return Object because the futures can be of any type
CompletableFuture<Object> weatherCF = CompletableFuture.any0f(cf1, cf2, cf3) ; // return Object 

// when there was a task ready, the completefuture is resolved for this task. The others are skipped
weatherCF.join();
Weather result = (Weather)weatherCF.get();
```

```java
CompletableFuture‹Weather> taskA = CompletableFuture.supplyAsync(fetchWeatherA);
CompletableFuture<Weather> taskB = CompletableFuture.supplyAsync(fetchWeatherB);

CompletableFuture.any0f(taskA, taskB)
        .thenAccept (System.out: :printIn)
        •join ();

```

## Run three task in parallel, and get the best result of them

```java
// create suppliers or callables, lambdas
Supplier<Weather> w1 = () -> getWeatherA();
Supplier<Weather> w2 = () -> getWeatherB();
Supplier<Weather> w3 = () -> getWeatherC();

// crete the completeFutures
CompletableFuture<Quotation> cf1 = CompletableFuture.supplyAsync(q1);
CompletableFuture<Quotation> cf2 = CompletableFuture.supplyAsync(q2);
CompletableFuture<Quotation> cf3 = CompletableFuture.supplyAsync(q3);

// pass the completeFutures
CompletableFuture<Void> done = CompletableFuture.all0f(cf1, cf2, cf3);

// to join, we have to join one by one and then do the join the final future
Quotation bestQuotation = 
        done.thenApply ( v ->
            Stream.of (cf1, cf2, cf3)
                .map (CompletableFuture: :join) // join one by one to resolve them
                .min (comparing (Quotation: :amount))
                .orElseThrow ())
        ).join();

```
## Composing Async Tasks: combining dependant tasks. Run two task in parallel, for creating the result of another object

first try, blocking for the response using a call method and blocking the main thread doing two get() calls

```java
// create CompletableFutures from suppliers
var quotationCF = CompletableFuture.supplyAsync(() -> getQuotation ( )) ;
var weatherCF = CompletableFuture.supplyAsync(() -> getWeather ()) ;

// here the result is blocking for the response, which is not optimus
var travelPage = new TravelPage (quotationCF .get (), weatherCF. get ()) ;
```
another variant using `thenCombine()`

```java
record class TravelPage(Quotation q, Weather w)

CompletableFuture<Weather> anyWeather =
        CompletableFuture.any0f(weatherCFs.toArray(CompletableFuture[]::new))
        .thenApply(o -> (Weather) o);    
    
CompletableFuture<Quotation> bestQuotationCF = 
        all0fQuotations.thenApply( v -> 
            quotationCFs.stream()
                .map (CompletableFuture: :join)
                .min (Comparator.comparing (Quotation:: amount))
                .orElseThrow()
        );
CompletableFuture‹TravelPage> pageCompletableFuture = bestQuotationCF.thenCombine(anyWeather,TravelPage::new);
// CompletableFuture‹TravelPage> pageCompletableFuture = bestQuotationCF.thenCombine(anyWeather,(q, w) -> TravelPage(q, w));
pageCompletableFuture.thenAccept(Svstem.out::println).join();
```

second try, using `allOf` method. Really complex, and you have to block the main thread doing a get() and join()

```java
// create CompletableFutures from suppliers
var quotationCF = CompletableFuture.supplyAsync(() -> getQuotation ( )) ;
var weatherCF = CompletableFuture.supplyAsync(() -> getWeather ()) ;

// here the result is blocking for the response, which is not optimus
var travelPage = new TravelPage (quotationCF .get (), weatherCF. get ()) ;

// create another completefuture, but it's not needed in this example
CompletableFuture<Void> done = CompletableFuture.all0f(quotationCF, weatherCF);

// here we can use thenApply and after that, we have to wait to resolve the futures 
and after that, to do join.
var travelPage = 
        done.thenApply (V -> 
            new TravelPage (quotationCF.get () , weatherCF.get ())
        .join()

```

third try, chaining the futures, you have to block the main thread doing a get() and join()

```java
// create CompletableFutures from suppliers
var quotationCF = CompletableFuture.supplyAsync(() -> getQuotation ( )) ;
var weatherCF = CompletableFuture.supplyAsync(() -> getWeather ()) ;

// here the result is blocking for the response, which is not optimus
var travelPage = new TravelPage (quotationCF .get (), weatherCF. get ()) ;

// create another completefuture, but it's not needed in this example
CompletableFuture<Void> done = CompletableFuture.all0f(quotationCF, weatherCF);

// here we can chain the futures and do a final join to resolve all of them
var travelPage =
        quotationCF.thenApply ( quotation ->
            new TravelPage (quotation, weatherCF .get ()
        ).join()) ;

```

fourth try, using thenCompose() method

```java
// create CompletableFutures from suppliers
var quotationCF = CompletableFuture.supplyAsync(() -> getQuotation ( )) ;
var weatherCF = CompletableFuture.supplyAsync(() -> getWeather ()) ;

// here the result is blocking for the response, which is not optimus
var travelPage = new TravelPage (quotationCF .get (), weatherCF. get ()) ;

// create another completefuture, but it's not needed in this example
CompletableFuture<Void> done = CompletableFuture.all0f(quotationCF, weatherCF);

// here we are going to compose or chain the futures, only a block with the final join(), 
//but in between is not blocking        
TravelPage travelPage = 
        quotationCF. thenCompose( quotation ->
            -> weatherCF.thenApply (
                weather -> new TravelPage (quotation, weather)) 
        ).join();
```

![wrapup.jpg](_img%2Fwrapup.jpg)

## Controlling your threads: join `pool`

Asynchronous tasks are executed in the `Common Fork / Join pool`

![wrapup-threads.jpg](_img%2Fwrapup-threads.jpg)

The number of threads is the number of CPU cores of the host machine

The `executor service` has one queue of waiting list to serve the messages to the consumers. Every consumer is a handle by a thread.

The `fork/join pool` has one queue of waiting list per every thread. And if the thread has consume all the messages in its queue or waiting list, 
then starts stealing messages from another neighbour waiting list of another thread.

![executorsevice_vs_joinpool.jpg](_img%2Fexecutorsevice_vs_joinpool.jpg)

By default, a task is executed in the same thread as the one that created it
By convention, if the methods ends with the "async" keyword, the function passed is executed in a new thread on the `join pool` 
otherwise, the same thread is keep on using it.

For instance `supplyAsync` is executed in another thread of the `join pool`

```java 
CompletableFuture<Quotation> quotationCF = 
        CompletableFuture. supplyAsync( () -> getQuotation ( ));
```

meanwhile `thenApply` is executed in the same thread.

```java 
CompletableFuture<Boolean> doneCF = 
        infosCF.thenApply (
            emailInfos -> writeToDB(emailInfos)
        );
```

```java
Executor executor = Executors.newFixedThreadPool (4);
// example for our custom executor
CompletableFuture<Quotation> quotationCF = 
        CompletableFuture. supplyAsync (
            () -> getQuotation (), executor);
        }

// here the SwingUtilities: :invokeLater implements the Executor interface, so you can use it. 
doneCF. thenApplyAsync(done -> updateGUI (done),
        SwingUtilities: :invokeLater);        
```

### ExecutorService as argument of async method to control the threads in which task are executing

The `async` methods accepts the `Executor` interface, so we can pass then or an `executorService` or a `lambda`.

```java
public interface Executor {
    void execute(Runnable task);
}
```



In the earlier examples, we can create some executors and apply them to the `supplyAsync` and `thenApplyAsync`, as second parameter

```java
ExecutorService quotationExecutor = Executors.newFixedThreadPool (4, quotationThreadFactory);
ExecutorService weatherExecutor = Executors.newFixedThreadPool (4, weatherThreadFactory);
ExecutorService minExecutor = Executors.newFixedThreadPool (1, minThreadFactory);

// pass the references to the async methods

//finally close the executors        
quotationExecutor.shutdown (); 
weatherExecutor.shutdown();
minExecutor.shutdown ();
```
we can pass from this execution

```bash
QB running in Thread[ForkJoinPool.commonPool-worker-5,5,main]
WB running in Thread[ForkJoinPool.commonPool-worker-2,5,main]
Q running in Thread [ForkJoinPool.commonPool-worker-6,5,main]
WC running in Thread[ForkJoinPool.commonPool-worker-3,5,main]
Q running in Thread[ForkJoinPool.commonPool-worker-4,5,main]
Allof then apply Thread[ForkJoinPool.commonPool-worker-3,5,main]
WA running in Thread[ForkJoinPool.commonPool-worker-1,5,main]
```

to this one, where all the executor are running

```bash
WB running in Thread[Weather-1,5,main]
QA running in Thread[Quotation-0,5,main]
QB running in Thread[Quotation-1,5,main]
WC running in Thread[Weather-2,5,main]
WA running in Thread[Weather-0,5,main]
Q running in Thread[Quotation-2,5,main]
Allof then apply Thread[ForkJoinPool.commonPool-worker-1,5, main]
TravelPage[quotation=Quotation[server=ServerA,amount=41],weather=Weather[server=ServerB,v
```

## handling exceptions

How to deal with exceptions: 

- exceptionally)
- whenComplete()
- handle()

We can use the `exceptionally()` or `handle (T, exception)` or `whenOnComplete(T, exception)`

```java
Supplier<Weather> w = () -> getWeather () ;
CompletableFuture<Weather> cf = 
        CompletableFuture.supplyAsync(w)
        .exceptionally(t -> new Weather (...));

// or handle

cf.handle ( (weather, exception) -> {
    if (exception != null) {
        logger.error (exception);
        return new Weather (...)
    } else{
        return weather;
    }
});

// or whenOnComplete

cf.whenComplete( (weather, exception) -> {
    if (exception != null)
    logger .error (exception);
});
```

## wrap up

Creating completable future with `supplier` and `runnable`:

![creating_completable_future_table.jpg](_img%2Fcreating_completable_future_table.jpg)

Creating completable future with `allOf` and `anyOf`:

![creating_completable_future_table_2.jpg](_img%2Fcreating_completable_future_table_2.jpg)

Creating completable future supported tasks:

![completable_future_supported_tasks.jpg](_img%2Fcompletable_future_supported_tasks.jpg)

Creating completable future chaining tasks:

![completable_future_chaining.jpg](_img%2Fcompletable_future_chaining.jpg)

Creating completable future chaining tasks, and get both values :

![completable_future_chaining_2.jpg](_img%2Fcompletable_future_chaining.jpg)

Creating completable future chaining tasks, and get a value:

![completable_future_chaining_3.jpg](_img%2Fcompletable_future_chaining_3.jpg)

## Conclusions

CompletionStage is interface
CompletionFuture is implementation

All these things are created for I/O tasks, no for inmemory tasks.
The CompletionStage API is useless for inmmemory tasks. The performance is worst. Decrease the performance and the throughput

One executorService is best, faster than having more executorServices.

Be carefully with the number of threads of every executorService.


![completableFutures.jpg](_img%2FcompletableFutures.jpg)

Next steps:

- Loom project for java, similar to kotlin coroutines

references:

- https://github.com/JosePaumard
- https://www.youtube.com/user/JPaumard