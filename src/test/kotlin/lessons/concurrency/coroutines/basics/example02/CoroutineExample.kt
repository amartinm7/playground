package lessons.concurrency.coroutines.basics.example02

import java.net.URL
import java.util.Random
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject

class CoroutineExample {

    private fun readJsonFrom(url: String) = URL(url).readText()

    fun execute() = runBlocking {
        val channel = Channel<String>(3)
        launch {
            channel.consumeEach {
                delay(1000L)
                Json.parseToJsonElement(it).jsonObject.getValue("id").also { println("id $it") }
            }
        }
        launch {
            channel.consumeEach {
                delay(2000L)
                Json.parseToJsonElement(it).jsonObject.getValue("userId").also { println("userId $it") }
            }
        }
        launch {
            channel.consumeEach {
                delay(3000L)
                Json.parseToJsonElement(it).jsonObject.getValue("title").also { println("title $it") }
            }
        }
        launch {
            channel.consumeEach {
                delay(4000L)
                Json.parseToJsonElement(it).jsonObject.getValue("completed").also { println("completed $it") }
            }
        }
        launch {
            repeat(20) {
                launch {
                    readJsonFrom(FAKE_URL(it))
                        .also { println(it) }
                        .let { channel.send(it) }
                }
            }
        }.invokeOnCompletion {
            channel.close()
        }
    }

    companion object {
        val FAKE_URL = { index: Int -> "https://jsonplaceholder.typicode.com/todos/${index + 1}" }
    }
}

fun main(): Unit = run { CoroutineExample().execute() }
