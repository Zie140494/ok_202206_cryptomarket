package ru.otus.otuskotlin.cryptomarket.openapi

import ru.otus.otuskotlin.cryptomarket.openapi.models.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {
    private val walletNumber = "123123123"
    private val createRequest = OrCreateRequest(
        or = OrCreateObject(
            walletNumber = walletNumber,
            fiatCurrency = FiatCurrency.RUB,
            cryptoCurrency = CryptoCurrency.BTC,
            action = Action.BUY
        )
    )

    @Test
    fun serialization(){
        val json = apiMapper.writeValueAsString(createRequest)
        println(json)

        assertTrue("json must serialize fiatCurrency field") {
            json.contains(""""walletNumber":"${walletNumber}"""")
        }
        assertTrue("json must serialize fiatCurrency field") {
            json.contains(""""fiatCurrency":"${FiatCurrency.RUB.value}"""")
        }
        assertTrue("json must serialize fiatCurrency field") {
            json.contains(""""cryptoCurrency":"${CryptoCurrency.BTC.value}"""")
        }
        assertTrue("json must serialize fiatCurrency field") {
            json.contains(""""action":"${Action.BUY.value}"""")
        }
    }

    @Test
    fun deserialization() {
        val json = apiMapper.writeValueAsString(createRequest)
        val obj = apiMapper.readValue(json, IRequest::class.java) as OrCreateRequest

        assertEquals(createRequest, obj)
    }
}