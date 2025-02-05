package lessons.concurrency.coroutines.basics.example01

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineBasics {
    fun execute() = runBlocking {
        launch {
            delay(4000L)
            println("worldYYY!!!")
        }
        println("HelloYYY")
    }

    fun execute2() = runBlocking {
        launch{
            doWorld()
        }
        println("HelloXXX")
    }
    suspend fun doWorld() {
        delay(1000L)
        println("worldXXX!!!")
    }
}

// Concurrently executes both sections
fun runInParallel() = runBlocking {
    repeat(50_000) { // launch a lot of coroutines
        launch {
            CoroutineBasics().execute()
        }
        launch {
            CoroutineBasics().execute2()
        }
    }
}

fun runInParallel2() = runBlocking {
    val job1 = launch {
        CoroutineBasics().execute()
    }
    val job2 = launch {
        CoroutineBasics().execute2()
    }
    job1.join()
    job2.join()

}

fun main() {
//    CoroutineBasics().execute()
//    CoroutineBasics().execute2()
    runInParallel()
}