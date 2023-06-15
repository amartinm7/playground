package play.akka.part4

import akka.actor.AbstractLoggingActor
import akka.actor.ActorSystem
import akka.actor.ExtendedActorSystem
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import com.typesafe.config.ConfigFactory

fun main(args: Array<String>) {

    class ScheduledActor : AbstractLoggingActor() {

        override fun preStart() {
            super.preStart()
            val actorSystem = context.system as ExtendedActorSystem
            QuartzSchedulerExtension(actorSystem)
                .schedule(
                    "HelloKotlin", // name of quartz job configured in akka config
                    self, // target actor
                    "hello kotlin" // message to send to target actor
                )
        }

        override fun createReceive(): Receive {
            return ReceiveBuilder()
                .match(String::class.java) { log().info(it) }
                .build()
        }

    }
    // instance system
    val dispatcherConfig = ConfigFactory.parseResources("play/akka/part3/dispatcher.conf")
    val actorSystem = ActorSystem.create("part4", dispatcherConfig)
    // register ScheduledActor
    actorSystem.actorOf(Props.create(ScheduledActor::class.java), "akka-quartz-scheduler")
    Thread.sleep(60000L)
    actorSystem.terminate()
}
