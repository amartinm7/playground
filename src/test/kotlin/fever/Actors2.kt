package com.amm.fever.infrastructure.services

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

data class Command(val data: String)

object Bus {
    private val subject = PublishSubject.create<Command>()
    val source: Flowable<Command> = subject.toFlowable(BackpressureStrategy.BUFFER)

    fun sink(cmd: Command) = subject.onNext(cmd)
}

fun main() {
    // Subscribe to the event bus and process each command asynchronously
    Bus.source
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .subscribe { command ->
            println("Received command: ${command.data}")
        }

    // Publish some commands
    Bus.sink(Command("Command 1"))
    Bus.sink(Command("Command 2"))
    Bus.sink(Command("Command 3"))

    // Wait for the processing to complete
    Thread.sleep(1000)
}