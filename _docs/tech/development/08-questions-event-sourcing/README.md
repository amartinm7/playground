# TODO

## do a CQRS implementation yes or yes

- develop/think in a write part to transforming/saving the data
- develop/think in a read part for serving the data

## Purchase problem: concurrency

Problem:

- you have a ticket to be sold. 

- You have several clients to buy the ticket.

Solution:

- create a queue (kafka) where you can enqueue the requests and have an order

- over the first client or message, block the database record with the `pessimistic locking` so only the first client can update the record on the database. Use `SELECT FOR UPDATE`

- We can acquire exclusive locks using ‘SELECT … FOR UPDATE' statements.

![pessimistic_lock.jpg](_img%2Fpessimistic_lock.jpg)

[testing-pessimistic-locking-handling-spring-boot-jpa](https://blog.mimacom.com/testing-pessimistic-locking-handling-spring-boot-jpa/#:~:text=Using%20the%20%22test%2Doracle%22,to%20your%20pessimistic%20locking%20handling)

[jpa-pessimistic-locking](https://www.baeldung.com/jpa-pessimistic-locking)

[pessimistic-locking-jpa-spring-boot](https://refactorizando.com/pessimistic-locking-jpa-spring-boot/)

## Select for update

SELECT FOR UPDATE is a SQL command that’s useful in the context of transactional workloads. It allows you to “lock” the rows returned by a SELECT query until the entire transaction that query is part of has been committed. Other transactions attempting to access those rows are placed into a time-based queue to wait, and are executed chronologically after the first transaction is completed.

This is useful because it prevents the thrashing and unnecessary transaction retries that would otherwise occur when multiple transactions are attempting to read those same rows. Any time multiple transactions are likely to be working with the same rows at roughly the same time, SELECT FOR UPDATE can be used to increase throughput and decrease tail latency (compared to what you would see without using it).

In other words: SELECT FOR UPDATE makes contended transactions process more smoothly (which generally also means they process more quickly and efficiently).

### Lock Modes
JPA specification defines three pessimistic lock modes that we're going to discuss:

- PESSIMISTIC_READ allows us to obtain a shared lock and prevent the data from being updated or deleted. If there is a pessimistic_read lock in a row, you can only read the row.
- PESSIMISTIC_WRITE allows us to obtain an exclusive lock and prevent the data from being read, updated or deleted. You have to wait until the lock is released until read, update or delete
- PESSIMISTIC_FORCE_INCREMENT works like PESSIMISTIC_WRITE, and it additionally increments a version attribute of a versioned entity.
- 
All of them are static members of the LockModeType class and allow transactions to obtain a database lock. They all are retained until the transaction commits or rolls back.

It's worth noting that we can obtain only one lock at a time. If it's impossible, a PersistenceException is thrown.

## CQRS

Do a write part and another query part

## Emit events

When an action occurs, send an event. (`publish and subscriber pattern`) but decouple the publisher and the subscribers.

the point is doing fire and forget for emitting domain events

Image you have a use case to save a domain object into database. After save it, you have to emit a domain event saying that something has occurs. This is the publisher. To decouple the execution of the publisher from the subscribers, we need an infrastructure piece like a database. In this way the use case, save into the business table, and into a domain event table. (or a kafka topic it's could be fine too)

After that, we have to implement a piece of code to read the domain event table or the kafka topic. For every row/message, we can send a request to the subscribers. The subscribers receive the request and do their job.

Handle with transactional event...

The events are store in a database/kafka (publishing), and another piece of code is reading the database/kafka (subscribing). In this way the transaccion is decoupled between the publisher and the subscribers. 

## database indexes

create indexes with several columns to add performance when a query refers to a several columns.

for instance: 
```sql
CREATE INDEX IF NOT EXISTS IDX_EVENT_START_DATE ON EVENT (START_DATE,END_DATE);
```

## how to measure the performance of the app

- Explain plan
- Locking: `pessimistic vs optimistic`
- understand how the database index works.
- database stress concurrent test
- how to measure the back and forth for the app with several infrastructure pieces.
- take into account what's happened if the `database` is not working. Do the test and resilence tasks
- take into account what's happened if the `external provider` is not working. Do the test and resilence tasks