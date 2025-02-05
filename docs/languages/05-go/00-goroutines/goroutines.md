# Goroutines

![fork-join-model.png](_img%2Ffork-join-model.png)

![fork-join-model-01.png](_img%2Ffork-join-model-01.png)

## The pipeline pattern

https://www.youtube.com/watch?v=qyM8Pi1KiiM

The pipeline pattern is a design pattern used in software engineering to process data. It is called a pipeline because it is similar to a series of connected pipes. Each pipe is a worker function that takes an input, processes it, and passes the output to the next pipe. The pipeline pattern is useful for processing large amounts of data in parallel.

### How it works

The pipeline pattern works by breaking down a large task into smaller tasks that can be processed in parallel. Each worker function in the pipeline processes a small part of the data and passes the output to the next worker function. This allows the data to be processed more quickly and efficiently than if it were processed sequentially.

In this case, the `sliceToChannel` func creates a `channel` and return it, `at the same time a function is created and executed async`. In this function the channel is filled one by one with the elements of the num slice. `Everytime that a element is sent to the channel, the func is blocked`, because the channel is full, until the channel is reading from the consumer in another function. When the main func receives the last element, the channel is closed and the for loop is breaked.

The `sq` function do the same. Creates a channel and execute a go routine which is reading from a channel and the result is sent to another channel. `The func is reading one by one, and is blocked meanwhile the output channel is not consumed by the main func`.

So the flow is  `sliceToChannel` reads the 2 number and sends it to the channel. Then stops until the `sq` func reads the number and sends the result to the output channel. The `sq` stops too until the main func is reading the output channel and printing the result.

So when the flow is finished with the 2 number, starts with the next number in the slice.

![pipeline-pattern-01.png](_img%2Fpipeline-pattern-01.png)

![pipeline-pattern-02.png](_img%2Fpipeline-pattern-02.png)

![pipeline-pattern-03.png](_img%2Fpipeline-pattern-03.png)

Keep in mind that the channel are created as write in the async function and returned as read-only for consuming. 

When the channel is closed, the range function exits too. 

As rule, you can read from a closed channel if you wish, but you don't write on it because is closed.

### importante

Creamos un channel con capacidad infinita. Cada vez que escribimos en el channel, el channel se bloquea hasta que el consumer del channel consume el valor. Asi pues, aunque tengamos una funcion async que escriba en el channel, mientras no sea consumido el channel, estará bloqueado esperando a que se consuma. 

Cada elemento es creado y consumido de seguido. En el ejemplo anterior, el numero 2 será incluido en el channnel, a continuacion será consumido en la funcion sq, y finalmente en el finalChannel. Cuando termine el proceso se continua con el numero 3 y asi sucesivamente.

