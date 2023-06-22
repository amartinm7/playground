package com.amm.fever.domain

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

fun main() {
    val source = Flux.create<Int> { emitter ->
        println(Thread.currentThread().name)
        emitter.next(1)
        emitter.complete()
    }

    source
        .flatMap { item ->
            Flux.defer { Flux.just(save(item)) }
                .subscribeOn(Schedulers.parallel())
        }
        .subscribe()

    // Wait for the processing to complete
    Thread.sleep(2000)
}

fun save(item: Int): String {
    val result = "Item $item saved"
    println("Saving item $item in thread ${Thread.currentThread().name}")
    // Add your save logic here
    return result
}