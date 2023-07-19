package lessons.algorithm.experiments

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Coroutines2 {
    suspend fun doPrintln(s: String) {
        delay(1000L)
        println(s)
    }

    suspend fun doPrintln2(s: String) = coroutineScope {  // this: CoroutineScope
        launch {
            delay(1300L)
            println("World!")
        }
        println("Hello")
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
            launch { Coroutines2().doPrintln("hello world") }
            Coroutines2().doPrintln2("jamon jamon")
        }
    }
}