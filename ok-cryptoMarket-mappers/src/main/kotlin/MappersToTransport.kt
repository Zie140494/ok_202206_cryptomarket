import models.CrmktOrPermissionClient
import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.exceptions.UnknownCrmktCommand
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.openapi.models.*



fun CrmktContext.toTransportOr(): IResponse = when (val cmd = command) {
    CrmktCommand.CREATE -> toTransportCreate()
    CrmktCommand.READ -> toTransportRead()
    CrmktCommand.UPDATE -> toTransportRead()
    CrmktCommand.DELETE -> toTransportRead()
    CrmktCommand.SEARCH -> toTransportRead()
    CrmktCommand.NONE -> throw UnknownCrmktCommand(cmd)
}

fun CrmktContext.toTransportCreate() = OrCreateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == CrmktState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    or = orResponse.toTransportOr()
)

fun CrmktContext.toTransportRead() = OrReadResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == CrmktState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    or = orResponse.toTransportOr()
)

fun CrmktContext.toTransportUpdate() = OrUpdateResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == CrmktState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    or = orResponse.toTransportOr()
)

fun CrmktContext.toTransportDelete() = OrDeleteResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == CrmktState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    or = orResponse.toTransportOr()
)

fun CrmktContext.toTransportSearch() = OrSearchResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == CrmktState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    ors = orsResponse.toTransportOr()
)


fun List<CrmktOr>.toTransportOr(): List<OrResponseObject>? = this
    .map { it.toTransportOr() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun CrmktOr.toTransportOr(): OrResponseObject = OrResponseObject(
    id = id.takeIf { it != CrmktOrId.NONE }?.asString(),
    walletNumber = walletNumber.takeIf { it.isNotBlank() },
    fiatCurrency = fiatCurrency.toTransportOr(),
    cryptoCurrency = cryptoCurrency.toTransportOr(),
    action = action.toTransportOr(),
    ownerId = ownerId.takeIf { it != CrmktUserId.NONE }?.asString(),
    permissions = permissionsClient.toTransportOr()
)

private fun Set<CrmktOrPermissionClient>.toTransportOr(): Set<OrPermissions>? = this
    .map { it.toTransportOr() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun CrmktOrPermissionClient.toTransportOr() = when (this) {
    CrmktOrPermissionClient.READ -> OrPermissions.READ
    CrmktOrPermissionClient.UPDATE -> OrPermissions.UPDATE
    CrmktOrPermissionClient.DELETE -> OrPermissions.DELETE
}

private fun List<CrmktError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportOr() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun CrmktError.toTransportOr() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun CrmktCryptoCurrency.toTransportOr(): CryptoCurrency? = when (this) {
    CrmktCryptoCurrency.BTC -> CryptoCurrency.BTC
    CrmktCryptoCurrency.UNKNOWN -> null
}

private fun CrmktFiatCurrency.toTransportOr(): FiatCurrency? = when (this) {
    CrmktFiatCurrency.RUB -> FiatCurrency.RUB
    CrmktFiatCurrency.UNKNOWN -> null
}

private fun CrmktAction.toTransportOr(): Action? = when (this) {
    CrmktAction.BUY -> Action.BUY
    CrmktAction.SELL -> Action.SELL
    CrmktAction.UNKNOWN -> null
}
