# Microservice Architecture Pattern

Topics 

- deployments
- kubernetes
- docker
- kafka
- event-drive architecture (ms) vs data architecture (monolith)
- CQRS: command query response segregation
- event-bus
- command-bus
- eventual consistency
- domain driven design DDD -> `event-storming` practice to identify the events and the streams
- bounded context: https://microservices.io/patterns/decomposition/decompose-by-subdomain.html, https://microservices.io/patterns/decomposition/decompose-by-business-capability.html
- cluster
- websockets https://www.wallarm.com/what/a-simple-explanation-of-what-a-websocket-is
- conversation patterns
- MSA microservice architecture -> single responsability principle
- scaling vertically vs horizontally
- DORA metrics
- circuit breaker
- Actors, Commands, Events, and Subscriptions


# What are the different types of performance tests?

- `Load Tests` Load testing simulates the number of virtual users that might use an application. 
In reproducing realistic usage and load conditions, based on response times, this test can help 
identify potential bottlenecks. It also enables you to understand whether it’s necessary 
to adjust the size of an application’s
- `Unit Tests` Unit testing simulates the transactional activity of a functional test campaign; 
the goal is to isolate transactions that could disrupt the system.
- `Stress Tests` Stress testing evaluates the behavior of systems facing peak activity. 
These tests significantly and continuously increase the number of users during the testing period.
- `Soak Tests` Soak testing increases the number of concurrent users and monitors the behavior of the system 
over a more extended period. The objective is to observe if intense and sustained activity over time shows a potential 
drop in performance levels, making excessive demands on the resources of the system.
- `Spike Tests` Spike testing seeks to understand implications to the operation of systems when activity levels are 
above average. Unlike stress testing, spike testing takes into account the number of users and the complexity of actions 
performed (hence the increase in several business processes generated).

related: 
- circuit breaker


## Devops

Monitoring - prometheus

![devops-01.jpg](_img%2Fdevops-01.jpg)

![devops-02.jpg](_img%2Fdevops-02.jpg)

![devops-03.jpg](_img%2Fdevops-03.jpg)

![devops-04.jpg](_img%2Fdevops-04.jpg)

![devops-05.jpg](_img%2Fdevops-05.jpg)

![devops-06.jpg](_img%2Fdevops-06.jpg)


## localstack

https://github.com/localstack/localstack