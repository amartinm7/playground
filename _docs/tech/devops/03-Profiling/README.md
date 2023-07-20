# Profiling

[example](https://www.jetbrains.com/help/idea/tutorial-find-a-memory-leak.html#check-the-fix)

Usually, the memory usage curve is saw-shaped: the chart goes up when new objects are allocated and periodically goes down when the memory is reclaimed by the garbage collector. You can see an example of this in the picture below:

![saw-teeth-memory-chart-2.png](_img%2Fsaw-teeth-memory-chart-2.png)

If the saw teeth become too frequent, it means that a lot of objects are being created and the garbage collector is called often to reclaim the memory. If we see a plateau (meseta), it means the garbage collector can’t free up any.

When the problem is resolved...

![no-leak.png](_img%2Fno-leak.png)