# Isolation levels

## Transaction Isolation
Isolation is one of the common ACID properties: Atomicity, Consistency, Isolation, and Durability. Isolation describes how changes applied by concurrent transactions are visible to each other.

Each isolation level prevents zero or more concurrency side effects on a transaction:

Dirty read: read the uncommitted change of a concurrent transaction
Nonrepeatable read: get different value on re-read of a row if a concurrent transaction updates the same row and commits
Phantom read: get different rows after re-execution of a range query if another transaction adds or removes some rows in the range and commits
We can set the isolation level of a transaction by @Transactional::isolation. It has these five enumerations in Spring: DEFAULT, READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE.

## levels

### Read uncommitted

Given two transactions over the same row at the same time: 
T1- is performing an insert/update
T2- is reading

![Read-Uncommitted-Isolation.png](_img%2FRead-Uncommitted-Isolation.png)

T1 blocks the rows, but allows read the row
T2 can read the row, but it can't perform an insert/update

### Read commited

Given two transactions over the same row at the same time:
T1- is performing an insert/update
T2- is reading

![Read-Committed-Isolation.png](_img%2FRead-Committed-Isolation.png)

T1 blocks the rows, and doesn't allow access the row by T2.
T2 neither can't read nor perform an insert/update

### Repeatable read

Given two transactions over the same row at the same time:
T1- is performing a reading, and it's blocking the row
T2- is reading or performing an insert/update

![RepeatablerRead-Isolation.png](_img%2FRepeatablerRead-Isolation.png)

T1 blocks the rows, and doesn't allow access the row by T2.
T2 neither can't read nor perform an insert/update

### Serializable

Given two transactions over the same row at the same time:
T1- is reading or performing an insert/update, it's blocking the table
T2- is reading or performing an insert/update

T1 blocks the table, and doesn't allow access the table by T2.
T2 neither can't read nor perform an insert/update

