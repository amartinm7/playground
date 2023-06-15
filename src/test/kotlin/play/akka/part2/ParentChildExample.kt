package play.akka.part2

import akka.actor.AbstractLoggingActor
import akka.actor.ActorInitializationException
import akka.actor.ActorKilledException
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.AllForOneStrategy
import akka.actor.DeathPactException
import akka.actor.Props
import akka.actor.SupervisorStrategy
import akka.japi.pf.ReceiveBuilder
import scala.concurrent.duration.Duration

fun main() {

    class ChildActor : AbstractLoggingActor() {

        override fun preStart() {
            super.preStart()
            log().info("Starting Child ActorX")
        }

        override fun createReceive() =
            ReceiveBuilder()
                .match(String::class.java, this::onMessage)
                .build()

        private fun onMessage(message: String) {
            when (message) {
                "DIE" -> throw Exception("DEAD")
                else -> log().info(message)
            }
        }
    }

    class ParentActor : AbstractLoggingActor() {

        //WARN restart all child Actors if one throws an exception with AllForOneStrategy(-1, Duration.Inf())
        override fun supervisorStrategy() = AllForOneStrategy(-1, Duration.Inf()) {
            when (it) {
                is ActorInitializationException -> SupervisorStrategy.stop()
                is ActorKilledException -> SupervisorStrategy.stop()
                is DeathPactException -> SupervisorStrategy.stop()
                else -> SupervisorStrategy.restart()
            }
        }

        override fun preStart() {
            super.preStart()
            context.actorOf(Props.create(ChildActor::class.java), "child1")
            context.actorOf(Props.create(ChildActor::class.java), "child2")
            context.actorOf(Props.create(ChildActor::class.java), "child3")
        }

        override fun createReceive() =
            ReceiveBuilder()
                .match(String::class.java) { context.children.forEach { child -> child.tell(it, self()) } }
                .build()
    }

    // create actor system, the engine itself
    val actorSystem = ActorSystem.create("part2")
    // create the parent actor. This actor will create the child actor inside
    val actorRef = actorSystem.actorOf(Props.create(ParentActor::class.java), "parent")
    //
    actorSystem.log().info("Sending Hello KotlinX")
    // send a message to the parent
    actorRef.tell("Hello Kotlin", ActorRef.noSender())
    //
    actorSystem.log().info("Sending DIE message to child1. We expect all child actors to restart")
    // die a child
    actorSystem.actorSelection("akka://part2/user/parent/child1").tell("DIE", ActorRef.noSender())
    // finish engine
    actorSystem.terminate()
}