package ru.otus.otuskotlin.cryptomarket.common.models

import models.CrmktOrPermissionClient

data class CrmktOr (
    var id: CrmktOrId = CrmktOrId.NONE,
    var ownerId: CrmktUserId = CrmktUserId.NONE,
    var walletNumber: String = "",
    var fiatCurrency: CrmktFiatCurrency = CrmktFiatCurrency.RUB,
    var cryptoCurrency: CrmktCryptoCurrency = CrmktCryptoCurrency.BTC,
    var action: CrmktAction = CrmktAction.BUY,
    val permissionsClient: MutableSet<CrmktOrPermissionClient> = mutableSetOf()
)
