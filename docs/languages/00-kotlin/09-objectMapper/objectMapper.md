# Object Mapper

Spring Boot 2.5.0 and InvalidDefinitionException: Java 8 date/time type `java.time.Instant` not supported by default

```kotlin
private val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
```