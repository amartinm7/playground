package play.algorithm.experiments

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Coroutines {
    suspend fun doPrintln(s: String) {
        delay(1000L)
        println(s)
    }

    suspend fun doPrintln2(s: String) = coroutineScope {  // this: CoroutineScope
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
    }
}



fun main(args: Array<String>) {
    runBlocking {
        launch { Coroutines().doPrintln("hello world") }
        Coroutines().doPrintln2("jamon jamon")
    }
}