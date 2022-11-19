package ru.otus.otuskotlin.cryptomarket.common.models


@JvmInline
value class CrmktOrId (private val id: String) {
    fun asString() = id

    companion object {
        val NONE = CrmktOrId("")
    }
}