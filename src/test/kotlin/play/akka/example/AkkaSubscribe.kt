import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.TypedActor
import akka.pattern.Patterns
import akka.pattern.Patterns

import akka.util.Timeout
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

// Define a message class for communication between actors
data class Greeting(val message: String, val requestId: String)

// Define a message class for subscribing to the topic
data class Subscribe(val topic: String, val subscriber: ActorRef)

// Define a message class for the response
data class GreetingResponse(val requestId: String, val result: Int)

// Define an actor class that subscribes to the topic and receives messages
class SubscriberActor : AbstractActor() {
    override fun preStart() {
        // Subscribe to the "greetings" topic
        context.system.eventStream().subscribe(self, Greeting::class.java)
    }

    override fun createReceive(): Receive {
        return receiveBuilder()
            .match(Greeting::class.java) { greeting ->
                // Process the greeting message and send back a response
                val result = greeting.message.length // Perform some computation
                val response = GreetingResponse(greeting.requestId, result)
                sender.tell(response, self) // Send the response back to the sender
            }
            .build()
    }
}

fun <T> akkaFutureToCompletableFuture(akkaFuture: scala.concurrent.Future<Any>): CompletableFuture<T> {
    val completableFuture = CompletableFuture<T>()
    akkaFuture.onComplete {
        when (it) {
            is akka.pattern.Status.Success -> completableFuture.complete(it.result() as T)
            is akka.pattern.Status.Failure -> completableFuture.completeExceptionally(it.cause())
        }
    }
    return completableFuture
}

fun main() {

    // Create an ActorSystem
    val system = ActorSystem.create("MyActorSystem")

    // Create instances of SubscriberActor
    val subscriberActor1: ActorRef = system.actorOf(Props.create(SubscriberActor::class.java))
    val subscriberActor2: ActorRef = system.actorOf(Props.create(SubscriberActor::class.java))

    // Generate unique request IDs
    val requestId1 = java.util.UUID.randomUUID().toString()
    val requestId2 = java.util.UUID.randomUUID().toString()

    // Publish Greeting messages to the "greetings" topic
    system.eventStream().publish(Greeting("Hello, Actors 1!", requestId1))
    system.eventStream().publish(Greeting("Hello, Actors 2!", requestId2))

    // Receive the responses from the SubscriberActors
    val response1: scala.concurrent.Future<Any> =
        PatternsCS.ask(subscriberActor1, Subscribe(requestId1, TypedActor.self()), Timeout(5, TimeUnit.SECONDS))
    val response2: scala.concurrent.Future<Any> =
        PatternsCS.ask(subscriberActor2, Subscribe(requestId2, TypedActor.self()), Timeout(5, TimeUnit.SECONDS))

    // Calculate the sum of the responses
    val sum = response1.onComplete<Any>()

    thenCombine(response2) { result1, result2 ->
        val responseList = listOf(result1, result2).mapNotNull { it as? GreetingResponse }
        responseList.sumBy { it.result }
    }

    val result: Int = sum.get()
    println("Sum of responses: $result")

    // Terminate the ActorSystem
    system.terminate()
}
