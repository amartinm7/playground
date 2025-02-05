package lessons.concurrency.coroutines.example4

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

data class TodoDto(val userId: Int, val id: Int, val title: String, val completed: Boolean)
class MyHttpClient {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/todos/"
    fun doGet(id: Int): TodoDto {
        val url = BASE_URL + id
        val request = Request.Builder().url(url).build();
        return OkHttpClient().newCall(request).execute().use {
            if (!it.isSuccessful) throw IOException("Unexpected code: $it")
            println("OkHttpClient $url")
            println("OkHttpClient $it")
            return@use jacksonObjectMapper().readValue(it.body!!.string(), TodoDto::class.java)
        }
    }
}


class Example4 {
    fun start() {
        execute(5)
    }

    private fun execute(times: Int)= runBlocking {
        repeat(times) { index ->
            val channel = Channel<TodoDto>()
            launch {
                val todoDto = MyHttpClient().doGet(index + 1)
                channel.send(todoDto)
                channel.close()
            }
            launch {
                val todoDto = channel.receive()
                MyHttpClient().doGet(todoDto.userId + index + 1)
            }
        }
    }
}

fun main() = runBlocking {
    Example4().start()
}