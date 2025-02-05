# Object Mapper

Spring Boot 2.5.0 and InvalidDefinitionException: Java 8 date/time type `java.time.Instant` not supported by default

```kotlin
private val objectMapper = ObjectMapper().registerModule(JavaTimeModule())
```

# Embedded Objects: nested objects outer and inner kotlin data classes

You have to use the @get:JsonUnwrapped and @get:JsonProperty because it works different in Kotlin than in Java.

```kotlin
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

data class Inner(
    @get:JsonProperty val age: Int,
    @get:JsonProperty val gender: String
)

data class Outer(
    @get:JsonUnwrapped val inner: Inner
)

fun main() {
    val inner = Inner(age = 25, gender = "male")
    val outer = Outer(inner = inner)

    val objectMapper = jacksonObjectMapper().registerKotlinModule()
    val jsonString = objectMapper.writeValueAsString(outer)

    println(jsonString)  // Output should be {"age":25,"gender":"male"}
}
```