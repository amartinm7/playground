# Buses

The idea:

- A service bus is a way of exchanging messages between components.
Messages are DTO's that contain information to act on.

-The "`sender component`" creates the message and passes it to the bus.

-The "`receiver component`" tells the bus what kind of messages it wants to receive.

-When the bus receives a message, it dispatches the message to the receiver(s).

-The bus serves as a boundary between components, it uncouples them. Both senders and receivers are unaware of the other components.

-Because of this decoupling, a service bus can allow (wildly) different components to work together efficiently.

-Because the bus is the intermediary of all messages, it can add functionality to all these messages without changing the messages, senders or receivers themselves. Examples are logging of all messages or queuing of messages.

There are three different buses:

- Command bus

- Query bus

- Event bus

## Command bus

- Messages (commands) signal the user's intention. Examples are CreateArticle or RegisterUser.

- One command is handled by exactly one handler.

- A command does not return any values.

## Query bus

- Messages (queries) signal a question, different from a database query. Examples are LatestArticles or CommentsForArticle.

- One query is handled by exactly one handler.
  
- Queries return data.

- Queries should not change the state of the application.

## Event bus

- Messages (events) signal an event has happened. Examples are ArticleWasCreated or UserWasRegistered.

- One event can be handled by any number of handlers ([0, inf]).

- Only holds primitives (strings, integers, booleans), not whole classes.

- Events should not return values.

## Bigger patterns

- Implementing commands and queries is part of the Command Query Responsibility Separation pattern ([CQRS](https://martinfowler.com/bliki/CQRS.html)). You can use service buses without applying CQRS.

- Commands and events are often used together. So, when command RegisterUser is done it fires the event UserWasRegistered. Read more at [From Commands To Events by Matthias Noback](https://matthiasnoback.nl/2015/01/from-commands-to-events/).