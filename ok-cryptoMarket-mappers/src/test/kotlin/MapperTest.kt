import org.junit.Assert.assertEquals
import org.junit.Test
import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.common.stubs.CrmktStubs
import ru.otus.otuskotlin.cryptomarket.openapi.models.*

class MapperTest {
    @Test
    fun fromTransport() {
        val req = OrCreateRequest(
            requestId = "1234",
            debug = OrDebug(
                mode = OrRequestDebugMode.STUB,
                stub = OrRequestDebugStubs.SUCCESS,
            ),
            or = OrCreateObject(
                walletNumber = "123456789",
                fiatCurrency = FiatCurrency.RUB,
                cryptoCurrency = CryptoCurrency.BTC,
                action = Action.BUY
            ),
        )

        val context = CrmktContext()
        context.fromTransport(req)

        assertEquals(CrmktStubs.SUCCESS, context.stubCase)
        assertEquals(CrmktWorkMode.STUB, context.workMode)
        assertEquals("123456789", context.orRequest.walletNumber)
        assertEquals(CrmktFiatCurrency.RUB, context.orRequest.fiatCurrency)
        assertEquals(CrmktCryptoCurrency.BTC, context.orRequest.cryptoCurrency)
        assertEquals(CrmktAction.BUY, context.orRequest.action)
    }

    @Test
    fun toTransport() {
        val context = CrmktContext(
            requestId = CrmktRequestId("1234"),
            command = CrmktCommand.CREATE,
            orResponse = CrmktOr(
                walletNumber = "123456789",
                fiatCurrency = CrmktFiatCurrency.RUB,
                cryptoCurrency = CrmktCryptoCurrency.BTC,
                action = CrmktAction.BUY
            ),
            errors = mutableListOf(
                CrmktError(
                    code = "err",
                    group = "request",
                    field = "title",
                    message = "wrong title",
                )
            ),
            state = CrmktState.RUNNING,
        )

        val req = context.toTransportOr() as OrCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("123456789", req.or?.walletNumber)
        assertEquals(FiatCurrency.RUB, req.or?.fiatCurrency)
        assertEquals(CryptoCurrency.BTC, req.or?.cryptoCurrency)
        assertEquals(Action.BUY, req.or?.action)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("title", req.errors?.firstOrNull()?.field)
        assertEquals("wrong title", req.errors?.firstOrNull()?.message)
    }
}
