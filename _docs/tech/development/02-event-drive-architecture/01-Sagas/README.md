# 01 - Sagas

reference: 
- https://microservices.io/patterns/data/saga.html

## Pattern: Saga

Every microservice has its own responsibility and aggregates. 
But sometimes, to do one action you need the information of another aggregate located in another microservice.
For instance, exists two microservices to handle the customers and the orders. 
Imagine that a customer wants to do an order. So when the microservice orders receives a request for an order, 
it doesn't know if the customer has credit or not to pay the order. 
So the orders microservice has to do a call to the customer microservice, and from this, the customer microservice 
ask for the credit of the user and do the operation of pay, and sends a domain event to the order microservice to 
confirm or reject the order. The order microservice reads the answer and confirm or reject the order as the incoming 
message say. Everything involved has to be transactional, in both services. This is what the `saga pattern` is about.

There are two ways of coordination sagas:

- `Choreography`: each local transaction publishes domain events that trigger local transactions in other services, but
the transmitter doesn't know who is going to read the event (message). Only emits a domain event.
- `Orchestration`: an orchestrator (object) tells the participants what local transactions to execute. It knows the process 
and the participants in the process. Emits events, but these domain events are `commands` (ordenes) to do in the target microservice.


### Example: Choreography-based saga

![Choreography-based saga](_img/choreography-saga.jpg)

### Example: Orchestration-based saga

![Orchestration-based saga](_img/orchestration-saga.jpg)

### Choreography-based saga vs Orchestration-based saga

In the `Choreography-based` example the transmitter emits a domain event `order created`, but it doesn't know who is going to 
consume the message. It's only saying that the `order is created`. 
In the `Choreography-based` example the transmitter emits a domain event `reserves credit` so it's asking another known microservice 
that it has to do something. It's doing a command (emite una orden)

## Saga is a transactional pattern

Meanwhile, the communication between the microservices is asynchronous (kafka messages), if there's a rollback, all the 
microservices has to do a rollback in the status of their aggregates. To do that, we need to compensate the aggregate 
with another actions. 

For instance, in the example before, if the order is pending, and the customer has not credited, the customer microservice, 
doesn't update the status on its database retaining the order amount, and emits an event to reject the order transaction. 
So when the order microservice reads the reject message, update the status of the order to reject. 

## Resulting context

This pattern has the following benefits:

- It enables an application to maintain data consistency across multiple services without using distributed transactions

This solution has the following drawbacks:

- The programming model is more complex. For example, a developer must design compensating transactions that explicitly undo changes made earlier in a saga.

There are also the following issues to address:

- In order to be reliable, a service must atomically update its database and publish a message/event. It cannot use the traditional mechanism of a distributed transaction that spans the database and the message broker. Instead, it must use one of the patterns listed below.

- A client that initiates the saga, which an asynchronous flow, using a synchronous request (e.g. HTTP POST /orders) needs to be able to determine its outcome. There are several options, each with different trade-offs:

  * The service sends back a response once the saga completes, e.g. once it receives an OrderApproved or OrderRejected event.
  * The service sends back a response (e.g. containing the orderID) after initiating the saga and the client periodically polls (e.g. GET /orders/{orderID}) to determine the outcome
  * The service sends back a response (e.g. containing the orderID) after initiating the saga, and then sends an event (e.g. websocket, web hook, etc) to the client once the saga completes.
