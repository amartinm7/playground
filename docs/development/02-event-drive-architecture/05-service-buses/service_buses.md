# 05 - Service buses

related
- https://barryvanveen.nl/articles/59-different-kinds-of-service-bus-command-bus-service-bus-and-query-bus

The last article was all about the command bus, a specific type of service bus. Now, let's take a step back and look at some other service buses. What similarities can we discover and how do they differ.

What is a service bus?
It is surprisingly hard to find an easy explanation of what a service bus is. When you read about the service bus on Wikipedia, you'll mostly see terminology from enterprise software development. Examples from Microsoft, IBM and Oracle are listed. Quite a different world from the PHP-driven web development world as I know it.

Let me try to summarize what a service bus is in my own words:

A service bus is a way of exchanging messages between components.
Messages are DTO's that contain information to act on.
The "sender component" creates the message and passes it to the bus.
The "receiver component" tells the bus what kind of messages it wants to receive.
When the bus receives a message, it dispatches the message to the receiver(s).
The bus serves as a boundary between components, it uncouples them. Both senders and receivers are unaware of the other components.
Because of this decoupling, a service bus can allow (wildly) different components to work together efficiently.
Because the bus is the intermediary of all messages, it can add functionality to all these messages without changing the messages, senders or receivers themselves. Examples are logging of all messages or queuing of messages.
Hopefully, this gives you a better feeling for what a service bus is. If not, read my previous article explaining what a command bus is. It is less abstract and contains some code examples.

Different buses
So far, we have discussed the "general" service bus. This bus just dispatches messages. It doesn't restrict these messages, or the handlers, in any way.

As you can imagine, different messages can and should be handled in a different way. That is why we have different kinds of service buses. The 3 buses I want to discuss are:

- Command bus
- Query bus
- Event bus

Let's look at some key features of each of these buses.

## Command bus

Messages (commands) signal the user's intention. Examples are CreateArticle or RegisterUser.
One command is handled by exactly one handler.
A command does not return any values.

## Query bus

Messages (queries) signal a question, different from a database query. Examples are LatestArticles or CommentsForArticle.
One query is handled by exactly one handler.
Queries return data.
Queries should not change the state of the application.

## Event bus

Messages (events) signal an event has happened. Examples are ArticleWasCreated or UserWasRegistered.
One event can be handled by any number of handlers ([0, inf]).
Only holds primitives (strings, integers, booleans), not whole classes.
Events should not return values.
As you can see, these buses are very similar. That is why they are so useful in my opinion. The concept of the bus is easy to understand and use. It adds structure and predictability to your application.

## Last remarks
Validation
Messages should always be valid. This means that a message object should validate its input. This way, only valid messages are dispatched. There is however a limit to this.

The registerUser command might require (among other things) a username. The command should validate that the username is a string with a length between 6 and 100 characters. Whether or not the username is unique should probably not be validated by the command but by the handler.

## Bigger patterns
Implementing commands and queries is part of the Command Query Responsibility Separation pattern (CQRS). You can use service buses without applying CQRS.

Commands and events are often used together. So, when command RegisterUser is done it fires the event UserWasRegistered. Read more at From Commands To Events by Matthias Noback.

## Further reading
If you would like to read more about service buses and messages:

Messaging Flavours by Mathias Verraes.
All Aboard The Service Bus by Robert Basic.
Some questions about the command bus by Matthias Noback.
Or read some of my other articles:

What is a command bus and why should you use it?.
How to composer require a private package.
Books, articles, videos and code examples that explain the SOLID design principles.