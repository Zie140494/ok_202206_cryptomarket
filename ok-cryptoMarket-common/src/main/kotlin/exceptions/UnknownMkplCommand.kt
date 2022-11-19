package ru.otus.otuskotlin.cryptomarket.common.exceptions

import ru.otus.otuskotlin.cryptomarket.common.models.CrmktCommand


class UnknownCrmktCommand(command: CrmktCommand) : Throwable("Wrong command $command at mapping toTransport stage")
