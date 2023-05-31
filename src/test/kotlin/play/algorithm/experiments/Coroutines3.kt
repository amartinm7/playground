package play.algorithm.experiments

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*

class Coroutines3 {
    suspend fun doPrintln(s: String) = coroutineScope {
        val future = async(context = Dispatchers.IO) {
            HttpClient().get("https://ktor.io/docs/").bodyAsText()
        }
        println(future.await())
    }

    suspend fun doPrintln2(s: String) = coroutineScope {  // this: CoroutineScope
        val future = async(context = Dispatchers.IO) {
            HttpClient().get("https://ktor.io/docs/").bodyAsText()
        }
        println(future.await())
    }
}

fun main(args: Array<String>) {
    var stillWorking = true
    runBlocking {
        while (stillWorking) {
            val keyboard = readLine()!!.trimEnd()
            if (keyboard == "stop") {
                stillWorking = false
            }
            launch { Coroutines3().doPrintln("hello world") }
            Coroutines3().doPrintln2("jamon jamon")
        }
    }
}