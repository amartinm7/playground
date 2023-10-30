# Simple publish / subscriber

Super simple,

- Create a consumer and producer as @service and @component respectively.
- Create a channel as a @bean and use it as dependency between the consumer and producer
- Use the producer to send messages throw the channel
- Use the consumer to receive messages throw the channel
- Every send and consume is executed as an isolated coroutine, in a different thread
- When springboot starts, the component EventConsumer is starter and wait for the messages into the channel

```kotlin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Configuration
class ChannelConfig {
    @Bean
    fun customEventChannel() = Channel<String>()
}

@Component
class EventConsumer(private val channel: Channel<String>) {
    init {
        GlobalScope.launch {
            for (message in channel) {
                println("${Thread.currentThread().name}: Received message: $message")
            }
        }
    }
}

@Service
class EventPublisher(private val channel: Channel<String>) {
    fun startPublishing() {
        GlobalScope.launch {
            while (true) {
                channel.send("${Thread.currentThread().name}: Hello, this is a custom event!")
                delay(1000) // Publish a message every 1 second
            }
        }
    }
}

@RestController
class Controller(private val publisher: CustomEventPublisher) {
    @GetMapping("/publish")
    fun starts(): Unit {
        publisher.startPublishing()
    }
}
```
