package lessons.concurrency.actormodel

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {

    data class Menu(val beans: String)

    suspend fun makeCoffee(ordersChannel: ReceiveChannel<Menu>, actorName: String) {
        for (order in ordersChannel) {
            println("${this.coroutineContext} Processing coffee... ")
            when (order) {
                is Menu -> println("$actorName do coffee $order")
                else -> println("don't do anything")
            }
        }
    }

    val orders = (0 .. 100).map { Menu("beans-$it") }

    val ordersChannel = Channel<Menu>()

    // we create two coroutines. Every one of them is creating and object passing the channel.
    // the channel stores the Menu object of every order.
    val spentTime = measureTimeMillis {
        launch(CoroutineName("actor-1")) {
            println("${this.coroutineContext}")
            makeCoffee(ordersChannel, "actor-1")
        }
        launch(CoroutineName("actor-2")) {
            println("${this.coroutineContext}")
            makeCoffee(ordersChannel, "actor-2")
        }
    }

    // this sentences are for sending the orders to the channel.
    // the channel stores the Menu object of every order.
    // there are some actors listening the channel to consume the messages one by one.
    // the channel has 1 item capacity in this example.
    // only an order is sent to the channel when the actor has consumed it and remove it from the channel.
    // Meanwhile the producer is suspended until the channel is empty.
    launch {
        for (order in orders) {
            delay(200)
            ordersChannel.send(order) // only can send a message until the message is consumed
        }
        ordersChannel.close()
    }

    println("execution time $spentTime")

}