# MockMVC

```kotlin
response = mockMvc.perform(
            get('/path/to/api')
            .header("Content-Type", "application/json"))

response.andExpect(status().isOk())
response.andReturn().getResponse().getContentAsString() == "what you expect"

mockMvc.perform(getItem()).andReturn().getResponse().getContentAsString()


mockMvc.perform(getRequest())
            .andExpect(MockMvcResultMatchers.status().isOk)
						.andReturn().getResponse().getContentAsString()


com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(user);
```

```kotlin
// Retorna el resultado de llamar a un endpoint
val json = mockMvc.perform(getSoldUserAdsHttpRequest())
.andExpect(status().isOk).andReturn()
.getResponse().getContentAsString();
```

JSONAssert

```xml
<dependency>
    <groupId>org.skyscreamer</groupId>
    <artifactId>jsonassert</artifactId>
    <version>1.5.0</version>
</dependency>
```

```kotlin
String actual = "{id:123, name:\"John\"}";
JSONAssert.assertEquals(
    "{id:123,name:\"John\"}", actual, JSONCompareMode.LENIENT);
```