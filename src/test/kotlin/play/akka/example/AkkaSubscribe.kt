package play.akka.example

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.pattern.Patterns
import akka.util.Timeout
import scala.concurrent.Future
import scala.concurrent.duration.Duration

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
    val responses: List<Future<Any>> = listOf(
        Patterns.ask(subscriberActor1, Subscribe(requestId1, subscriberActor1), Timeout(Duration.fromNanos(5000L))),
        Patterns.ask(subscriberActor2, Subscribe(requestId2, subscriberActor2), Timeout(Duration.fromNanos(5000L))),
    )

    // Combine and calculate the sum of the responses
//    val aggregatedResponse: Future<List<GreetingResponse>> =
//        Future.sequence(responses)
//            .map { results ->
//                results.mapNotNull { it as? GreetingResponse }
//            }

//    val sum: Int =
//        Await.result(aggregatedResponse, Duration.fromNanos(5000L))
//            .map { it.result }
//            .sum()
//
//    println("Sum of responses: $sum")

    // Terminate the ActorSystem
    // system.terminate()
}
