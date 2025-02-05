# BFF Pattern vs Gateway Pattern

This article covers

- BFF pattern
- Gateway pattern
- difference between BFF and Api gateway
- When to use which

# **BFF Pattern**

BFF Layer is basically an orchestrator layer which is also called as **Backend for frontend**. When an orchestrator layer includes aggregation, computation, composition of some data then it’s more than a simple API gateway.

BFF is mostly used to support its client request with one-size-fit all API

**What is expected from BFF layer?**

- First and foremost, **providing only the data what client needs**, not more or not less. Large and unnecessary payload always adds up the latency.
- Maximum throughput, **# request per unit time**. That truly depends upon the technology & the right approach being used.
- **Less response times**. This can be done by asynchronously calling other services and some other slicing & dicing out.
- It should be **resilient by design with proper implementation of circuit breakers, timeouts, retry etc**. Reason being, it is a single point of failure for your client so it has to be thoroughly loaded tested.
- If one or more service is taking too much time, then it is okay to return partial result but not in the case of Listing page where we need to show all of the data.
- **Use of caching** would be good in case of failures. There are some data which need not to synced every seconds or minute. Find out what are those and cache it with proper TTL.

# **BFF Pattern vs Api Gateway Pattern**

![bff_01.webp](_img%2Fbff_01.webp)

Api Gateway

While an **API Gateway is a single point of entry into the system for all clients**, a **BFF is only responsible for a single type of client**. Let’s assume your system has two (typical) clients: A Single Page Application (SPA) and a mobile client (Android, iOS). In this case, each client would get its own API Gateway, which would therefore be a *BFF*.

![bff_02.webp](_img%2Fbff_02.webp)

backend for frontend (BFF)

in other word **API gateway is a single point of entry for all clients fetching data from the system, while a [backend-for-frontend (BFF)](https://golden-sprinkles-da9432.netlify.app/blog/api-management/2022/05/05/api-gateway-vs-backend-for-frontend/) is tailored to a specific frontend** — typically with multiple BFFs corresponding to multiple frontends. However, to better understand the reasoning behind each approach, it is worth considering their differences.

**A BFF is actually a type of API Gateway**. In fact, both perform the same function, with the main difference being in scope — or how many clients they interact with. **A BFF being tailored to the requirements a specific client, usually a frontend view of an application, while an API gateway generally functions as a single gateway for the majority (or all) of the clients to access data**

# **When to use which**

When we have to decide whether we want to use the API Gateway or the BFF approach, there are several indicators that can help us to decide between the two approaches.

In case there are several clients we have to ask ourselves:

- How different are the needs of each client? Do we need to support different communication protocols? One client might use [GraphQL](https://graphql.org/) and the other one REST APIs with JSON.
- Are the clients owned by different teams? In this case it might make sense to use the BFF pattern, because many times we don’t want components with shared ownership in a microservice system. Each team could then have full ownership over its own BFF.
- Are we performing aggregation in the API Gateway? Although many off-the-shelve implementations of API Gateways, like [Amazons API Gateway](https://aws.amazon.com/api-gateway/) and [Kong](https://konghq.com/kong) do not support to this functionality, a custom solution might do so. In this case, it’s very likely that after time the aggregations will be different for each type of client (think about the different screen-real-estate between mobile and desktop). This would be an additional indicator for us to use an API Gateway.
- Are the clients using different authentication mechanisms? In this case, it might make sense to use BFFs instead of an API Gateway that supports multiple authentication mechanisms that are only used by a single client.

How similar is the handling of the different clients? Especially when it comes to cross-cutting concerns like the authentication mechanism, rate limiting, and data aggregation if we find that there is a significant overlap between the different clients we need to ask ourselves whether this overlap can be handled on the configuration level of the framework we are using or whether we would need to duplicate code