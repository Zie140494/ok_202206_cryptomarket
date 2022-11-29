package ru.otus.otuskotlin.cryptomarket.common.models

@JvmInline
value class CrmktUserId (private val id: String) {
    fun asString() = id

    companion object {
        val NONE = CrmktUserId("")
    }
}