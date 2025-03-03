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

## ObjectMapper with OffsetDateTime

```kotlin
    @JsonDeserialize(using = OffsetDateTimeDeserializer::class)
    @JsonSerialize(using = OffsetDateTimeSerializer::class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssX")
    val myDate: OffsetDateTime
```

```kotlin
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer
import java.io.IOException
import java.time.OffsetDateTime
import kotlin.Throws

class OffsetDateTimeDeserializer : JsonDeserializer<OffsetDateTime>() {
    @Throws(IOException::class)
    override fun deserialize(jsonParser: JsonParser, context: DeserializationContext): OffsetDateTime =
        InstantDeserializer.OFFSET_DATE_TIME.deserialize(jsonParser, context)
}
```

```kotlin
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer
```

### ObjectMapper

```kotlin
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

object DPObjectMapperDependencies {
  val objectMapper: ObjectMapper by lazy {
    ObjectMapper()
      .findAndRegisterModules()
      .setSerializationInclusion(JsonInclude.Include.NON_NULL)
      .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
      .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
      .configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false)
  }

  val jsonSchemaFetcher: JsonSchemaFetcher by lazy { JsonSchemaFactoryJsonSchemaFetcher() }
}

---

        import com.fasterxml.jackson.annotation.JsonCreator
        import com.fasterxml.jackson.annotation.JsonValue

        data class SchemaUrl @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(val value: String) {
    @JsonValue override fun toString(): String = value
}

---
// validate json schema

        import com.event.base.SchemaUrl
        import com.networknt.schema.JsonMetaSchema
        import com.networknt.schema.JsonSchema
        import com.networknt.schema.JsonSchemaFactory
        import com.networknt.schema.Version4
        import java.net.URI

class JsonSchemaFactoryJsonSchemaFetcher(private val jsonSchemaFactory: JsonSchemaFactory) : JsonSchemaFetcher {
    constructor() :
            this(
                JsonSchemaFactory.builder()
                    .defaultMetaSchemaIri(Version4().instance.iri)
                    .metaSchema(JsonMetaSchema.getV4())
                    .build())

    override fun fetch(schemaUrl: SchemaUrl): JsonSchema = jsonSchemaFactory.getSchema(URI.create(schemaUrl.value))
}
```