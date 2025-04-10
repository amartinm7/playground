# Feign decoder

```kotlin
package com.configuration

import feign.Response
import feign.codec.ErrorDecoder
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfig {
  @Bean
  fun feignErrorDecoder(): FeignErrorDecoder = FeignErrorDecoder()
}

class FeignErrorDecoder : ErrorDecoder {
  private val logger = LoggerFactory.getLogger(FeignErrorDecoder::class.java)

  override fun decode(methodKey: String, response: Response): Exception {
    val body = response.body()?.asInputStream()?.bufferedReader()?.use { it.readText() }
    logger.error("Error en Feign Client: método=$methodKey, status=${response.status()}, body=$body")
    return when (response.status()) {
      422 -> UnprocessableStatementException("Error 422: $body")
      else -> Exception("Error desconocido: $body")
    }
  }
}

```

compare JSON
```kotlin
JSONAssert.assertEquals("{json}", objectMapper.writeValueAsString(object), JSONCompareMode.LENIENT);
```