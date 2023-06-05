# Microservices world

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
- cluster
- websockets https://www.wallarm.com/what/a-simple-explanation-of-what-a-websocket-is
- conversation patterns
- MSA microservice architecture -> single responsability principle
- scaling vertically vs horizontally
- DORA metrics
- circuit breaker

You are developing a business-critical enterprise application. You need to deliver changes rapidly, frequently and reliably - as measured by the DORA metrics - in order for your business to thrive in today’s volatile, uncertain, complex and ambiguous world. Consequently, your engineering organization is organized into small, loosely coupled, cross-functional teams. Each team delivers software using DevOps practices as defined by the DevOps handbook. In particular, it practices continuous deployment. The team delivers a stream of small, frequent changes that are tested by an automated deployment pipeline and deployed into production.

https://microservices.io/patterns/microservices.html

![microservices-patterns](_img/microservices-patterns.jpg)

20-30 min de charla dónde te pedimos que directamente nos cuentes un proyecto del que te sientas orgulloso y domines
(2 min para ponernos en contexto del tu ROL en ese proyecto y 1 min la parte funcional) y luego nos lo bajes a nivel técnico.
(elección de tecnologías, bbdd, escalabilidad, rendimiento, arquitectura...etc...

Number of requests per second/minute.
Failed requests per second.
Average response time per service endpoint.
Distribution of time required for each request.
Average execution time for the fastest 10% and slowest 10% queries.
Success/failure rate by service.