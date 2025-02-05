# DORA metrics

https://premium.microservices.io/

![dora-feature.png](_img%2Fdora-feature.png)

Many software applications become unmaintainable. Applications devolve into a tangled and fragile mess that take forever to change. This usually happens because organizations neglect fundamental software development principles. As a result, the build up of technical debt eventually leads to a crisis.

A good way for an organization to prevent this mess is to hold its leaders accountable for measuring and improving the performance and productivity of the software development organization. In this article, I describe one set of metrics that organizations should track and improve: the DORA metrics. I cover what good looks like for each metric. I also briefly describe how to capture and improve these metrics.

## Building bad software quickly (and how to avoid it)

Over the past year, I’ve talked to numerous developers who have said

our application is ‘****’, it’s so complex and so difficult to change

I’d expect to hear comments like this about ancient legacy systems. But I’m also hearing the exact same comments about systems that are only a couple of years old. It seems that far too often our shiny new architectures, tools and technologies simply accelerate the development of bad software.

My explanation is that organizations are so focused on delivering features that they neglect the fundamentals of software design. In this article, I’ll talk about four often neglected fundamentals of software design:

- Design loosely coupled software
- Actively reduce complexity through modularity
- Design for fast feedback
- You are (probably not) special - ignore the fundamentals at your peril

If you regularly, neglect these fundamentals, you’ll end up with a system that’s difficult to change. Conversely, if you pay attention to them, you will build higher quality and healthy software. Let’s look at each of these fundamentals in turn starting with designing loosely coupled software.