rootProject.name = "ok_202206_cryptomarket"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val openApiVersion: String by settings

        kotlin("jvm") version kotlinVersion
        id("org.openapi.generator") version openApiVersion

    }
}

include("ok-cryptomarket-transport-main-openapi")
include("ok-cryptomarket-common")
include("ok-cryptoMarket-mappers")