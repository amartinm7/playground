package com.amm.fever.infrastructure.services.test

import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.PublishSubject

sealed class Command {

    object MakeCoffee: Command()

    data class LaunchMissiles(val missileType: String): Command()

    class DoaBarrelRoll: Command() {
        fun barrelRoll() {
            println("do whatever")
        }
    }
}

object Bus {
    private val subject = PublishSubject.create<Command>()
    val source = subject.toFlowable(BackpressureStrategy.BUFFER)
    fun sink(cmd: Command) =
        subject.onNext(cmd)
}

class Connection {
    // ...
    fun onMessageReceived(msg: String) =
        when (msg) {
            "Command.MakeCoffee" ->
                Bus.sink(Command.MakeCoffee)
            "Command.LaunchMissiles" -> Bus.sink(Command.LaunchMissiles("hello"))
            "Command.DoaBarrelRoll" -> Bus.sink(Command.DoaBarrelRoll())
            else -> {}
        }
}

object Actor {
    // ...
    private val disposable = Bus.source
        // .filter { it: Command -> it is Command.MakeCoffee }
        .subscribe(Actor::handle)
    // ...
    private fun handle(cmd: Command) {
        println("MakeCoffee do whatever")
    }
}

fun main() {
    Connection().onMessageReceived("Command.MakeCoffee")

    Thread.sleep(5000)
}