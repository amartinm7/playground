package com.example.playground.pubsubs

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
class CustomEventListener(private val channel: Channel<String>) {
    init {
        GlobalScope.launch {
            for (message in channel) {
                println("${Thread.currentThread().name}: Received message: $message")
            }
        }
    }
}

@Service
class CustomEventPublisher(private val channel: Channel<String>) {

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
