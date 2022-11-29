package ru.otus.otuskotlin.cryptomarket.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.common.stubs.CrmktStubs


data class CrmktContext (
    var command: CrmktCommand = CrmktCommand.NONE,
    var state: CrmktState = CrmktState.NONE,
    val errors: MutableList<CrmktError> = mutableListOf(),
    var workMode: CrmktWorkMode = CrmktWorkMode.PROD,
    var stubCase: CrmktStubs = CrmktStubs.NONE,
    var requestId: CrmktRequestId = CrmktRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var orRequest: CrmktOr = CrmktOr(),
    var orFilterRequest: CrmktOrFilter = CrmktOrFilter(),
    var orResponse: CrmktOr = CrmktOr(),
    var orsResponse: MutableList<CrmktOr> = mutableListOf()
)