package ru.otus.otuskotlin.cryptomarket.springapp.api.service

import cryptomarket.stubs.OrderStub
import org.springframework.stereotype.Service
import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.models.*
import ru.otus.otuskotlin.cryptomarket.common.stubs.CrmktStubs
import ru.otus.otuskotlin.cryptomarket.springapp.api.errorResponse
import ru.otus.otuskotlin.cryptomarket.springapp.api.successResponse
import ru.otus.otuskotlin.markeplace.springapp.common.notFoundError


@Service
class OrService {

    fun createOr(crContext: CrmktContext): CrmktContext {
        val response = when (crContext.workMode) {
            CrmktWorkMode.PROD -> TODO()
            CrmktWorkMode.TEST -> crContext.orRequest
            CrmktWorkMode.STUB -> OrderStub.getModel()
        }
        return crContext.successResponse {
            orResponse = response
        }
    }

    fun readOr(mpContext: CrmktContext): CrmktContext {
        val requestedId = mpContext.orRequest.id

        return when (mpContext.stubCase) {
            CrmktStubs.SUCCESS -> mpContext.successResponse {
                orResponse = OrderStub.getModel().apply { id = requestedId }
            }
            else -> mpContext.errorResponse {
                it.copy(field = "or.id", message = notFoundError(requestedId.asString()))
            }
        }
    }

    fun updateOr(context: CrmktContext) = when (context.stubCase) {
        CrmktStubs.SUCCESS -> context.successResponse {
            orResponse = OrderStub.getModel {
                if (orRequest.id != CrmktOrId.NONE) id = orRequest.id
                if (orRequest.walletNumber.isNullOrEmpty()) walletNumber = orRequest.walletNumber
                if (orRequest.fiatCurrency != CrmktFiatCurrency.UNKNOWN) fiatCurrency = orRequest.fiatCurrency
                if (orRequest.cryptoCurrency != CrmktCryptoCurrency.UNKNOWN) cryptoCurrency = orRequest.cryptoCurrency
                if (orRequest.action != CrmktAction.UNKNOWN) action = orRequest.action
            }
        }
        else -> context.errorResponse {
            it.copy(field = "or.id", message = notFoundError(context.orRequest.id.asString()))
        }
    }


    fun deleteOr(context: CrmktContext): CrmktContext = when (context.stubCase) {
        CrmktStubs.SUCCESS -> context.successResponse {
            orResponse = OrderStub.getModel { id = context.orRequest.id }
        }
        else -> context.errorResponse {
            it.copy(
                field = "or.id",
                message = notFoundError(context.orRequest.id.asString())
            )
        }
    }

    fun searchOr(context: CrmktContext): CrmktContext {
        val filter = context.orFilterRequest

        val searchableString = filter.searchString

        return when(context.stubCase) {
            CrmktStubs.SUCCESS -> context.successResponse {
                orsResponse.addAll(
                    OrderStub.getModels()
                )
            }
            else -> context.errorResponse {
                it.copy(
                    message = "Nothing found by $searchableString"
                )
            }
        }
    }
}

