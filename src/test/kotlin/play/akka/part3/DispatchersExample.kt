package play.akka.part3

import akka.actor.AbstractLoggingActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder
import com.typesafe.config.ConfigFactory

val theConfigFile =
"""
actor1-dispatcher {
    type = Dispatcher
    executor = "thread-pool-executor"
    thread-pool-executor {
        fixed-pool-size = 16
    }
    throughput = 1
}

actor2-dispatcher {
    type = PinnedDispatcher
    executor = "thread-pool-executor"
}

akka.actor.deployment {
    /actor1 {
        dispatcher = actor1-dispatcher
    }
}

akka.quartz {
  schedules {
    HelloKotlin {
      description = "A Hello Kotlin Task!"
      expression = "*/5 * * ? * *"
    }
  }
}
"""

//Dispatcher (default) — One mailbox per Actor, backed by a thread pool, and can be shared across multiple actors. Since the thread pool is shared, this is recommended for non-blocking actors that perform short lived tasks.
//
//Pinned Dispatcher — One mailbox per Actor, backed by a single threaded pool, and cannot be shared across actors (ie the Actor is assigned it’s own thread). Useful for Actors that perform long running, blocking tasks (such as Database Calls).
//
//CallingThreadDispatcher — One mailbox per Actor per Thread and does not create any new threads. In other words, the thread that sends a message to an actor is also used to deliver and process the message. This is useful for testing to create deterministic behaviour.


fun main(args: Array<String>) {

    // Actor class
    class HelloKotlinActor : AbstractLoggingActor() {
        override fun createReceive() = ReceiveBuilder().match(String::class.java) { log().info(it) }.build()
    }
    //
    val dispatcherConfig = ConfigFactory.parseResources("play/akka/part3/dispatcher.conf")
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