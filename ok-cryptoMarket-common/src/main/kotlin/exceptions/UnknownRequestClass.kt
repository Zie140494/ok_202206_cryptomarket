package ru.otus.otuskotlin.cryptomarket.common.exceptions

class UnknownRequestClass(clazz: Class<*>): RuntimeException("Class $clazz cannot be mapped to MkplContext")
