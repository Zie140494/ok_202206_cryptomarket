package ru.otus.otuskotlin.cryptomarket.springapp.api.controller

import org.springframework.web.bind.annotation.*
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.mappers.*
import ru.otus.otuskotlin.cryptomarket.openapi.models.*
import ru.otus.otuskotlin.cryptomarket.springapp.api.service.OrService

@RestController
@RequestMapping("or")
class OrController(
    private val orService: OrService
) {

    @PostMapping("create")
    fun createOr(@RequestBody createOrRequest: OrCreateRequest): OrCreateResponse {
        val context = CrmktContext().apply { fromTransport(createOrRequest) }

        return orService.createOr(context).toTransportCreate()
    }

    @PostMapping("read")
    fun readOr(@RequestBody readOrRequest: OrReadRequest) =
        CrmktContext().apply {
            fromTransport(readOrRequest)
        }.let {
            orService.readOr(it)
        }.toTransportRead()

    @RequestMapping("update", method = [RequestMethod.POST])
    fun updateOr(@RequestBody updateOrRequest: OrUpdateRequest): OrUpdateResponse {
        return CrmktContext().apply {
            fromTransport(updateOrRequest)
        }.let {
            orService.updateOr(it)
        }.toTransportUpdate()
    }

    @PostMapping("delete")
    fun deleteOr(@RequestBody deleteOrRequest: OrDeleteRequest): OrDeleteResponse {
        val context = CrmktContext().apply { fromTransport(deleteOrRequest) }

        val result = orService.deleteOr(context)

        return result.toTransportDelete()
    }

    @PostMapping("search")
    fun searchOr(@RequestBody searchOrRequest: OrSearchRequest) =
        CrmktContext().apply { fromTransport(searchOrRequest) }.let {
            orService.searchOr(it)
        }.toTransportSearch()
}