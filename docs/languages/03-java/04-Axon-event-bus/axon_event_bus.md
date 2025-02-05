# Axon event bus Java

## Step 1: Add Dependencies

Add the necessary dependencies to your pom.xml file:

Maven
```pom.xml
<!-- Axon Framework dependencies -->
<dependency>
    <groupId>org.axonframework</groupId>
    <artifactId>axon-spring-boot-starter</artifactId>
    <version>4.5.2</version>
</dependency>
<dependency>
    <groupId>org.axonframework</groupId>
    <artifactId>axon-test</artifactId>
    <version>4.5.2</version>
    <scope>test</scope>
</dependency>
```

## Step 2: Define the Domain Event

Define the OfferSavedEvent class as before.

```java
public class OfferSavedEvent {
    private final String offerId;
    // other relevant fields and methods
    
    public OfferSavedEvent(String offerId) {
        this.offerId = offerId;
    }
    
    public String getOfferId() {
        return offerId;
    }
}
```

## Step 3: Create the Event Handler

Create an event handler to handle the OfferSavedEvent:

```java
@Component
public class OfferSavedEventHandler {

    @EventHandler
    public void handleOfferSavedEvent(OfferSavedEvent event) {
        // Perform necessary actions based on the event
        System.out.println("Received OfferSavedEvent: " + event.getOfferId());
    }
}
```

## Step 4: Configure Axon

Configure Axon by creating a configuration class:

```java
@Configuration
public class AxonConfig {

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerEventHandler(c -> new OfferSavedEventHandler());
    }
}
```

## Step 5: Emit the Domain Event

Emit the OfferSavedEvent using Axon's EventBus:

```java
@Service
public class OfferService {

    private final EventBus eventBus;

    public OfferService(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void save(Offer offer) {
        // Save the offer

        // Emit the domain event
        OfferSavedEvent event = new OfferSavedEvent(offer.getId());
        eventBus.publish(GenericEventMessage.asEventMessage(event));
    }
}
```

## Step 6: Configure Axon Spring Boot

Configure Axon Spring Boot by adding the following property to your application.properties file:

application.yml or properties
```bash
axon.axonserver.servers=127.0.0.1:8124
```

