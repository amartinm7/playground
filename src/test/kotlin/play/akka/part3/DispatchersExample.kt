package play.akka.part3

import akka.actor.AbstractLoggingActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder
import com.typesafe.config.ConfigFactory

fun main(args: Array<String>) {

    // Actor class
    class HelloKotlinActor : AbstractLoggingActor() {
        override fun createReceive() = ReceiveBuilder().match(String::class.java) { log().info(it) }.build()
    }
    //
    val dispatcherConfig = ConfigFactory.parseResources("play/akka/part3/part3.conf")
    val actorSystem = ActorSystem.create("part3", dispatcherConfig)
    // instance the actors in the system
    val actorRef1 = actorSystem.actorOf(Props.create(HelloKotlinActor::class.java), "actor1")
    val actorRef2 =
        actorSystem.actorOf(Props.create(HelloKotlinActor::class.java).withDispatcher("actor2-dispatcher"), "actor2")
    //
    actorSystem.log().info(">>> Sending Hello Kotlin")
    //
    actorRef1.tell("Hello Actor 1", ActorRef.noSender())
    //
    actorRef2.tell("Hello Actor 2", ActorRef.noSender())
    //
    actorSystem.terminate()
}