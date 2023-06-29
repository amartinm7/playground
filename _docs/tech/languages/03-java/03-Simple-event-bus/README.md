# Simple event bus

## Step 1: Create the Event Bus

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

## Step  2: Create the Event Bus Interface

```kotlin
public interface EventBus {
    void publish(OfferSavedEvent event);
    void subscribe(Consumer<OfferSavedEvent> eventHandler);
    void unsubscribe(Consumer<OfferSavedEvent> eventHandler);
}
```

## Step  3: Implement the Event Bus

Create a class that implements the EventBus interface and uses a simple list of event handlers to publish and subscribe to events:

```java
@Component
public class SimpleEventBus implements EventBus {
    private final List<Consumer<OfferSavedEvent>> eventHandlers = new ArrayList<>();

    @Override
    public void publish(OfferSavedEvent event) {
        for (Consumer<OfferSavedEvent> eventHandler : eventHandlers) {
        eventHandler.accept(event);
    }
    }

    @Override
    public void subscribe(Consumer<OfferSavedEvent> eventHandler) {
        eventHandlers.add(eventHandler);
    }

    @Override
    public void unsubscribe(Consumer<OfferSavedEvent> eventHandler) {
        eventHandlers.remove(eventHandler);
    }
}

```

## Step 4: Emit the Domain Event


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
        eventBus.publish(event);
    }
}
```

## Step 5: Subscribe to the Domain Event

```java
@Component
public class OfferSavedEventSubscriber {
    private final EventBus eventBus;

    public OfferSavedEventSubscriber(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.subscribe(this::handleOfferSavedEvent);
    }

    public void handleOfferSavedEvent(OfferSavedEvent event) {
        // Perform necessary actions based on the event
        System.out.println("Received OfferSavedEvent: " + event.getOfferId());
    }
}

```