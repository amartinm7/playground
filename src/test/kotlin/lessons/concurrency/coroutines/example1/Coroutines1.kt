package lessons.concurrency.coroutines.example1

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Coroutines1 {

    fun readURL() = runBlocking {// creates a coroutine here because starts a coroutines scope
        val response: String = HttpClient(CIO).get("http://google.com/").bodyAsText()
        println(response.substring(0, 100))
        println(">>> readURL thread: ${Thread.currentThread().name}")
    }

    suspend fun differentThread() = withContext(Dispatchers.Default) {// creates a coroutine here because starts a coroutines scope
        println(">>> differentThread thread ${Thread.currentThread().name}")
    }

    suspend fun longRunningTask(){
        println(">>> Executing longRunningTask on...: ${Thread.currentThread().name}")
        delay(1000)  // simulating the slow behavior by adding a delay
        println(">>> longRunningTask ends on thread ...: ${Thread.currentThread().name}")
    }
}

fun main() = runBlocking { // creates a coroutine here because starts a coroutines scope
    println(">>> Main thread: ${Thread.currentThread().name}")
    val c = Coroutines1()
    c.readURL()
    c.differentThread()
    val job1:Job = launch(Dispatchers.Default) {// creates a coroutine here because starts a coroutines scope // starting a coroutine
        c.longRunningTask()  // calling the long-running function
    }
    // launch is fire and forget
    val job2:Job = launch(Dispatchers.Default) {// creates a coroutine here because starts a coroutines scope // starting a coroutine
        c.longRunningTask()  // calling the long-running function
    }
    val defer = async(Dispatchers.IO) {// creates a coroutine here because starts a coroutines scope
        c.longRunningTask()
    }
    //blocks and return a value
    defer.await()
    //blocks is like await
    job1.join()
    job2.join()

    println(">>> Before ending...")
    delay(10000)
}