package ru.otus.otuskotlin.cryptomarket.common.models

data class CrmktOrFilter(
    var searchString: String = "",
    var ownerId: CrmktUserId = CrmktUserId.NONE,
)
