package cryptomarket.stubs

import models.CrmktOrPermissionClient
import ru.otus.otuskotlin.cryptomarket.common.models.*


object OrderStub {
    private fun stubReady() = CrmktOr(
        walletNumber = "",
        fiatCurrency = CrmktFiatCurrency.RUB,
        cryptoCurrency = CrmktCryptoCurrency.BTC,
        action = CrmktAction.BUY,
    )

    private fun stubInProgress() = CrmktOr(
        id = CrmktOrId(id = "123456789"),
        ownerId = CrmktUserId(id = "1945"),
        walletNumber = "987654321",
        fiatCurrency = CrmktFiatCurrency.RUB,
        cryptoCurrency = CrmktCryptoCurrency.BTC,
        action = CrmktAction.SELL,
        permissionsClient = mutableSetOf(CrmktOrPermissionClient.UPDATE)
    )

    fun getModel(model: (CrmktOr.() -> Unit)? = null) = model?.let {
        stubReady().apply(it)
    } ?: stubReady()

    fun getModels() = listOf(
        stubReady(),
        stubInProgress()
    )

    fun CrmktOr.update(updateableOr: CrmktOr) = apply {
        walletNumber = updateableOr.walletNumber
        fiatCurrency = updateableOr.fiatCurrency
        cryptoCurrency = updateableOr.cryptoCurrency
        action = updateableOr.action
        permissionsClient.addAll(updateableOr.permissionsClient)
    }
}
