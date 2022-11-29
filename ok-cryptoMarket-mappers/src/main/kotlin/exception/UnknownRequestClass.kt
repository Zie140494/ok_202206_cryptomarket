package ru.otus.otuskotlin.cryptomarket.mappers.exception

class UnknownRequestClass (clazz: Class<*>): RuntimeException("Class $clazz cannot be mapped to CrmktContext")