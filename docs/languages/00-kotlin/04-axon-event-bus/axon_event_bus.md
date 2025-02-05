# Axon event bus kotlin

## Step 1: Add Dependencies

Add the necessary dependencies to your build.gradle.kts file:

```build.gradle.kts
implementation("org.springframework.boot:spring-boot-starter-web")
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
implementation("org.axonframework.extensions.kotlin:axon-kotlin-extensions:4.5.2")
implementation("org.axonframework:axon-spring-boot-starter:4.5.2")
implementation("org.axonframework:axon-test:4.5.2")
```

##  Step 2: Define the Domain Event

```kotlin
data class OfferSavedEvent(val offerId: String)
```

## Step 3: Create the Event Handler

```kotlin
@Component
class OfferSavedEventHandler {

    @EventHandler
    fun handleOfferSavedEvent(event: OfferSavedEvent) {
        // Perform necessary actions based on the event
        println("Received OfferSavedEvent: ${event.offerId}")
    }
}

```

## Step 4: Configure Axon

```kotlin
@Configuration
class AxonConfig {

    @Autowired
    fun configure(configurer: EventProcessingConfigurer) {
        configurer.registerEventHandler { OfferSavedEventHandler() }
    }
}
```

## Step 5: Emit the Domain Event

```kotlin
@Service
class OfferService(private val eventBus: EventBus) {

    fun save(offer: Offer) {
        // Save the offer

        // Emit the domain event
        val event = OfferSavedEvent(offer.id)
        eventBus.publish(GenericEventMessage.asEventMessage(event))
    }
}
```

## Step 6: Configure Axon Spring Boot

Configure Axon Spring Boot by adding the following property to your application.properties file:

```bash
axon.axonserver.servers=127.0.0.1:8124
```

That's it! With these steps, you have emitted a domain event "OfferSaved" in your Spring Boot application using DDD and hexagonal architecture, written in Kotlin. Axon will handle the publication and consumption of events for you, invoking the appropriate event handler whenever an OfferSavedEvent is published.