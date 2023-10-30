# DefaultConsumerErrorHandler

```kotlin
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.messaging.support.ErrorMessage
import org.springframework.stereotype.Component

private const val UNHANDLED_EXCEPTION = "unhandled-exception"

@Component
class DefaultConsumerErrorHandler(private val meterRegistry: MeterRegistry) {
    private val logger = LoggerFactory.getLogger(DefaultConsumerErrorHandler::class.java)

    @ServiceActivator(inputChannel = "errorChannel")
    fun handleError(message: ErrorMessage) {
        logger.error("Failed Kafka message: ${String(message.originalMessage?.payload as ByteArray)}", message.payload)
        meterRegistry.counter(UNHANDLED_EXCEPTION).increment()
        logger.error("$UNHANDLED_EXCEPTION -  ${message.payload.message}", message.payload)
    }
}
```