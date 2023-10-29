# Profiling

reference:
https://www.jetbrains.com/help/idea/profiler-intro.html

## example

[example](https://www.jetbrains.com/help/idea/tutorial-find-a-memory-leak.html#check-the-fix)

## How is profiling working?

Click on the profile button to start. 

You have to leave it an amount of time to get sample of tests.

After a while, you can stop it into the profiler tab (bottom part in the IDE)

![profiler-flame-graph-1.png](_img%2Fprofiler-flame-graph-1.png)

Then you can check the graphics, and over the top of the logs (blue part or yellow), you can execute `code -> analyze code -> dataflow from here` to navigate up to the source code.

The code is shown, and here you can setup a break point to investigate in another execution

Once you have debugged, you can fix the problem and recheck everything.

## Garbage collector smells

Usually, the memory usage curve is saw-shaped: the chart goes up when new objects are allocated and periodically goes down when the memory is reclaimed by the garbage collector. You can see an example of this in the picture below:

![saw-teeth-memory-chart-2.png](_img%2Fsaw-teeth-memory-chart-2.png)

If the saw teeth become too frequent, it means that a lot of objects are being created and the garbage collector is called often to reclaim the memory. If we see a plateau (meseta), it means the garbage collector can’t free up any.

When the problem is resolved...

![no-leak.png](_img%2Fno-leak.png)