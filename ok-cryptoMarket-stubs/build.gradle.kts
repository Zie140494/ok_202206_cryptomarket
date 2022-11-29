plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))

    // transport models
    implementation(project(":ok-cryptomarket-common"))

    testImplementation(kotlin("test-junit"))
}
