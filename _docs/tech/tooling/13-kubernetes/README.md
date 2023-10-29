# Kubernetes

## Kubernetes Requests vs Limits
Good article to setup the CPU boundary limits
https://home.robusta.dev/blog/stop-using-cpu-limits


![ms-example-architecture.webp](_img%2Fms-example-architecture.webp)


![deploy-container.svg](..%2F..%2F..%2F..%2F..%2F..%2FIdeaProjects%2Fnotes%2Fdocs%2Fgo%2Fonboarding%2F14-profiling-kubernetes%2F_img%2Fdeploy-container.svg)

## What is CPU Throttling

CPU throttling means that applications are granted more constrained resources when they are near to the container’s CPU limit. In some cases, container throttling occurs even when CPU utilization is not close to the limit due to bugs in the Linux kernel.

Consider a single-threaded application running on a limited CPU with a processing time of 200ms per operation. The following diagram shows an application that completes the request:

![cpu-throttling-01.jpeg](_img%2Fcpu-throttling-01.jpeg)

Now consider an application with a CPU limit of 0.4 CPUs. The application will only receive about 40ms of runtime for each 100ms. This means that instead of completing the request in 200ms, it will take a total of 440ms. This means the application is experiencing CPU throttling.

![cpu-throttling-02.jpeg](_img%2Fcpu-throttling-02.jpeg)

## Best practices for CPU limits and requests on Kubernetes

Lets summarize:

- Use CPU requests for everything (if you need help setting them, see KRR)
- Make sure they are accurate
- Do not use CPU limits.

`CPU limits` vs `memory limits` are different topics

## What about memory limits and requests?
Everything in this post is about CPU and not memory. Memory is different because it is non-compressible - once you give memory you can't take it away without killing the process. We've covered the best practices for Kubernetes memory limits here. In short, our bottom line recommendation is:

Always use memory limits
Always use memory requests
Always set your memory requests equal to your limitsS

