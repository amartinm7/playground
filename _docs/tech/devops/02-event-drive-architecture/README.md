# 01 - Event Drive Architecture

reference: 
- https://www.confluent.io/blog/journey-to-event-driven-part-1-why-event-first-thinking-changes-everything/


## intro

At the beginning, the systems were designed thinking in data, on databases, tables and rows. 

Nowadays, the paradigm is changing in favour to event driven data architecture. 
That is, we have some microservices with several responsibilities, and they are communicating 
using messages (event sourcing). These messages are produced when something is happened into the ms core domain. 
For instance, an message is comming and consumed into a microservice, a row is stored into the database. 
This triggers another event, and produces an message to send to another kafka-topic. 
From here, there are some consumers who do something with these messages. 
And the process is repeated another time as it's explained previously.

- A sequence of related events represents a behavior: An item is added to and then removed from a shopping cart;
- A sequence of related events is commonly called a "stream."
- we start with the event rather than the highly-coupled concept of a command: "an event X has occurred," rather than "command Y should be executed."
- We think of streams and events much like a database table and rows.
- Streams and events are the basic building blocks of a data platform. 
In an event-driven streaming architecture, we use a "key" to create a logical grouping of events as a stream, similar to what 
we do with a database table. Streams represent the core data model, 
and stream processors are the connecting nodes that enable us to create flows, resulting in a streaming data topology. 
Stream processor patterns enable filtering, projections, joins, aggregations, materialized views, and other streaming functionality. 
Unlike in the table-and-rows model, events are front and center.

##  Importance of Event-Driven Architecture

To understand the importance of being event-driven, we’ll examine why events have become so pivotal in our thinking today. 
We will then evaluate the qualities and how events have become a first-class concern for the modern organization, 
as awareness of events underpins event-first thinking and design. 
I’ll then extrapolate design concepts against different perspectives of event patterns, such as the event command 
and event first, and why event-driven programming fulfills foundational requirements when choosing an architecture that 
supports evolution and elasticity.

## event-first approach