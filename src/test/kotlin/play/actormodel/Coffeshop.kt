package play.actormodel

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    data class Menu(val beans: String)

    suspend fun makeCoffee(ordersChannel: ReceiveChannel<Menu>) {
        for (order in ordersChannel) {
            println("${this.coroutineContext} Processing coffee... ")
            when (order) {
                is Menu -> println("do coffee $order")
                else -> println("don't do anything")
            }
        }
    }

    val orders = listOf(Menu("1"), Menu("2"), Menu("3"), Menu("4"))

    val ordersChannel = Channel<Menu>()

    val spentTime = measureTimeMillis {
        launch(CoroutineName("actor-1")) {
            println("${this.coroutineContext}")
            makeCoffee(ordersChannel)
            delay(2000)
        }
        launch(CoroutineName("actor-2")) {
            println("${this.coroutineContext}")
            makeCoffee(ordersChannel)
            delay(1000)
        }
    }

    // this sentences is for sending the orders to the channel.
    // there are some actors listening the channel to consume the messages one by one
    launch {
        for (order in orders) {
            ordersChannel.send(order) // only can send a message until the message is consumed
        }
        ordersChannel.close()
    }

    println("execution time $spentTime")

}