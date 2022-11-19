package ru.otus.otuskotlin.cryptomarket.common.models

@JvmInline
value class CrmktRequestId (private val id:String){
    fun asString() = id

    companion object {
        val NONE = CrmktRequestId("")
    }

}