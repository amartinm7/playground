import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert

class JsonUnwrapperTest {

    @Test
    fun `should validate the brand object is embedded into the request and response objects`() {
        //GIVEN
        val brand = Brand("brandId", "brand")
        val request = MyCustomRequest(brand)
        val response = MyCustomResponse(brand, "externalId")

        //WHEN
        val requestJson = ObjectMapper().writeValueAsString(request)
        val responseJson = ObjectMapper().writeValueAsString(response)

        //THEN
        JSONAssert.assertEquals(requestJson, EXPECTED_REQUEST, true)
        JSONAssert.assertEquals(responseJson, EXPECTED_RESPONSE, true)
    }

    companion object {
        private const val EXPECTED_REQUEST = """
                {
                    "id": "brandId",
                    "name": "brand"
                }
            """
        private const val EXPECTED_RESPONSE = """
                {
                    "id": "brandId",
                    "name": "brand",
                    "externalId": "externalId"
                }
            """
    }
}

data class Brand(@JsonProperty("id") val id: String, @JsonProperty("name") val name: String)

data class MyCustomRequest(@get:JsonUnwrapped val brand: Brand)

data class MyCustomResponse(@get:JsonUnwrapped val brand: Brand, @JsonProperty("externalId") val externalId: String)
