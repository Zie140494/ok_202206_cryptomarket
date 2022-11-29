package ru.otus.otuskotlin.cryptomarket.springapp.api

import ru.otus.otuskotlin.cryptomarket.common.CrmktContext
import ru.otus.otuskotlin.cryptomarket.common.models.CrmktError
import ru.otus.otuskotlin.cryptomarket.common.models.CrmktState
import ru.otus.otuskotlin.cryptomarket.openapi.models.ResponseResult


fun CrmktContext.errorResponse(error: (CrmktError) -> CrmktError) =
    apply {
        state = CrmktState.FAILING
        errors.add(error(buildError()))
    }


fun CrmktContext.successResponse(context: CrmktContext.() -> Unit) = apply(context)
    .apply {
        state = CrmktState.RUNNING
    }

private fun buildError() = CrmktError(
    field = "_", code = ResponseResult.ERROR.value
)
