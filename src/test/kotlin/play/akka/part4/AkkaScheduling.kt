package play.akka.part4

import akka.actor.AbstractLoggingActor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.japi.pf.ReceiveBuilder
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {

    data class ScheduledMessage(val message: String)

    class ScheduledActor : AbstractLoggingActor() {

        override fun createReceive(): Receive {
            return ReceiveBuilder()
                .match(ScheduledMessage::class.java) { schedule(it) }
                .match(String::class.java) { log().info(it) }
                .build()
        }

        private fun schedule(message: ScheduledMessage) =
            // schedule the message to do it in the future
            context.system.scheduler()
                .scheduleOnce(
                    Duration.create(100, TimeUnit.MILLISECONDS), // delay
                    self, // target actor ref
                    message.message, // message to put in the targets mailbox
                    context.system.dispatcher(), // dispatcher to use
                    self // sender actor ref
                )
    }

    // instance the system, the engine
    val actorSystem = ActorSystem.create("part4")
    //
    val repeatingScheduler = actorSystem.actorOf(Props.create(ScheduledActor::class.java), "once-off-scheduler")
    // send a schedule message
    repeatingScheduler.tell(ScheduledMessage("hello kotlin"), ActorRef.noSender()) // schedule the repeating task
    //
    Thread.sleep(1000L)
    actorSystem.terminate()
}