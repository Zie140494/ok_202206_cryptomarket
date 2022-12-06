package ru.otus.otuskotlin.cryptomarket.mappers

import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.common.stubs.CrmktStubs
import ru.otus.otuskotlin.cryptomarket.mappers.exception.UnknownRequestClass
import ru.otus.otuskotlin.cryptomarket.openapi.models.*

fun CrmktContext.fromTransport(request: IRequest) = when(request){
    is OrCreateRequest -> fromTransport(request)
    is OrReadRequest -> fromTransport(request)
    is OrUpdateRequest -> fromTransport(request)
    is OrDeleteRequest -> fromTransport(request)
    is OrSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun String?.toOrId() = this?.let { CrmktOrId(it) } ?: CrmktOrId.NONE
private fun BaseOrIdRequestOr?.toOrWithId() = CrmktOr(id = this?.id.toOrId())
private fun IRequest?.requestId() = this?.requestId?.let { CrmktRequestId(it) } ?: CrmktRequestId.NONE

private fun OrDebug?.transportToWorkMode(): CrmktWorkMode = when(this?.mode) {
    OrRequestDebugMode.PROD -> CrmktWorkMode.PROD
    OrRequestDebugMode.TEST -> CrmktWorkMode.TEST
    OrRequestDebugMode.STUB -> CrmktWorkMode.STUB
    null -> CrmktWorkMode.TEST
}

private fun OrDebug?.transportToStubCase(): CrmktStubs = when(this?.stub) {
    OrRequestDebugStubs.SUCCESS -> CrmktStubs.SUCCESS
    OrRequestDebugStubs.NOT_FOUND -> CrmktStubs.NOT_FOUND
    OrRequestDebugStubs.BAD_ID -> CrmktStubs.BAD_ID
    OrRequestDebugStubs.CANNOT_DELETE -> CrmktStubs.CANNOT_DELETE
    OrRequestDebugStubs.BAD_SEARCH_STRING -> CrmktStubs.BAD_SEARCH_STRING
    OrRequestDebugStubs.BAD_WALLET -> CrmktStubs.BAD_WALLET
    OrRequestDebugStubs.BAD_CRYPTO_CURRENCY -> CrmktStubs.BAD_CRYPTO_CURRENCY
    OrRequestDebugStubs.BAD_FIAT_CURRENCY -> CrmktStubs.BAD_FIAT_CURRENCY
    null -> CrmktStubs.NONE
}

fun CrmktContext.fromTransport(request: OrCreateRequest) {
    command = CrmktCommand.CREATE
    requestId = request.requestId()
    orRequest = request.or?.toInternal() ?: CrmktOr()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun CrmktContext.fromTransport(request: OrReadRequest) {
    command = CrmktCommand.READ
    requestId = request.requestId()
    orRequest = request.or.toOrWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun CrmktContext.fromTransport(request: OrUpdateRequest) {
    command = CrmktCommand.UPDATE
    requestId = request.requestId()
    orRequest = request.or?.toInternal() ?: CrmktOr()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun CrmktContext.fromTransport(request: OrDeleteRequest) {
    command = CrmktCommand.DELETE
    requestId = request.requestId()
    orRequest = request.or.toOrWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun CrmktContext.fromTransport(request: OrSearchRequest) {
    command = CrmktCommand.DELETE
    requestId = request.requestId()
    orFilterRequest = request.orFileter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun OrSearchFilter?.toInternal(): CrmktOrFilter = CrmktOrFilter(
    searchString = this?.searchString ?: ""
)

private fun OrCreateObject.toInternal(): CrmktOr = CrmktOr(
    walletNumber = walletNumber ?: "",
    fiatCurrency = this.fiatCurrency.fromTransport(),
    cryptoCurrency = this.cryptoCurrency.fromTransport(),
    action = this.action.fromTransport()
)

private fun OrUpdateObject.toInternal(): CrmktOr = CrmktOr(
    id = this.id.toOrId(),
    walletNumber = walletNumber ?: "",
    fiatCurrency = this.fiatCurrency.fromTransport(),
    cryptoCurrency = this.cryptoCurrency.fromTransport(),
    action = this.action.fromTransport()
)

private fun FiatCurrency?.fromTransport():  CrmktFiatCurrency = when(this) {
    FiatCurrency.RUB -> CrmktFiatCurrency.RUB
    null -> CrmktFiatCurrency.UNKNOWN
}

private fun CryptoCurrency?.fromTransport():  CrmktCryptoCurrency = when(this) {
    CryptoCurrency.BTC -> CrmktCryptoCurrency.BTC
    null -> CrmktCryptoCurrency.UNKNOWN
}

private fun Action?.fromTransport():  CrmktAction = when(this) {
    Action.BUY -> CrmktAction.BUY
    Action.SELL -> CrmktAction.SELL
    null -> CrmktAction.UNKNOWN
}

