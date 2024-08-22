package lessons.concurrency.coroutines.example3

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

// Simulating an API call to fetch cities
suspend fun fetchCitiesFromMicroservice(): List<String> {
    delay(1000) // Simulating network delay
    println("fetchCitiesFromMicroservice")
    return listOf("City1", "City2", "City3")
}

// Simulating an API call to send a city and get the address
suspend fun sendCityAndGetAddress(city: String): String {
    delay(1000) // Simulating network delay
    println("sendCityAndGetAddress: $city")
    return "Address for $city ${Thread.currentThread().name}"
}

// Simulating an API call to send an address and get related zip codes
suspend fun sendAddressAndGetZipCodes(address: String): List<String> {
    delay(1000) // Simulating network delay
    println("sendAddressAndGetZipCodes: $address")
    return listOf("ZipCode1", "ZipCode2", "ZipCode3")
}

// Simulating saving a zip code to the database
suspend fun saveZipCodeToDatabase(zipCode: String) {
    delay(1000) // Simulating database save delay
    println("Zip code saved to database: $zipCode ${Thread.currentThread().name}")
}

suspend fun main() {
    runBlocking {
        val cities = fetchCitiesFromMicroservice()

        val citiesChannel = Channel<String>()
        val addressesChannel = Channel<String>()
        val zipCodesChannel = Channel<String>()

        // Launch a coroutine to send cities to the channel
        launch {
            cities.forEach { city ->
                citiesChannel.send(city)
            }
            citiesChannel.close()
        }

        // Launch a coroutine to send addresses to the channel
        launch {
            for (city in citiesChannel) {
                val address = sendCityAndGetAddress(city)
                addressesChannel.send(address)
            }
            addressesChannel.close()
        }

        // Launch a coroutine to send zip codes to the channel
        launch {
            for (address in addressesChannel) {
                val zipCodes = sendAddressAndGetZipCodes(address)
                zipCodes.forEach { zipCode ->
                    zipCodesChannel.send(zipCode)
                }
            }
            zipCodesChannel.close()
        }

        // Launch a coroutine to save zip codes to the database concurrently
        launch(Dispatchers.Default) {
            for (zipCode in zipCodesChannel) {
                launch {
                    saveZipCodeToDatabase(zipCode)
                }
            }
        }
    }
}
