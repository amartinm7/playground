package play.akka.part1

import akka.actor.AbstractLoggingActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder

fun main() {
    data class Greeting(val message: String, val requestId: String)
    // create actor1
    class HelloKotlinActor : AbstractLoggingActor() {
        override fun createReceive() =
            ReceiveBuilder()
                .match(String::class.java) { log().info(it) }.build()
    }

    // create actor2
    class HelloKotlinActor2 : AbstractLoggingActor() {
        override fun createReceive() =
            ReceiveBuilder()
                .match(String::class.java) { log().info(it) }
                .match(Greeting::class.java) { log().info(it.message) }
                .build()
    }

    // create actor system, the engine
    val actorSystem = ActorSystem.create("part1")

    // create actor system, the engine
    val actorRef1 = actorSystem.actorOf(Props.create(HelloKotlinActor::class.java))
    val actorRef2 = actorSystem.actorOf(Props.create(HelloKotlinActor2::class.java))

    // log output message
    actorSystem.log().info(">>> Sending Hello Kotlin")

    // send message
    actorRef1.tell("Hello KotlinX", ActorRef.noSender())
    actorRef2.tell(Greeting(message = "Hello worldX", requestId = "id1" ), ActorRef.noSender())
    actorSystem.terminate()
}