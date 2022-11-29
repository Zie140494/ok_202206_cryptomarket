import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = rootProject.group
version = rootProject.version
java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    kotlin("jvm")
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
}

dependencies {
    val kotestVersion: String by project

    implementation("org.springframework.boot:spring-boot-starter-actuator") // info; refresh; springMvc output
    implementation("org.springframework.boot:spring-boot-starter-web") // Controller, Service, etc..
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // from models to json and Vice versa
    implementation("org.jetbrains.kotlin:kotlin-reflect") // for spring-boot app
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // for spring-boot app

    // transport models
    implementation(project(":ok-cryptomarket-common"))
    implementation(project(":ok-cryptomarket-transport-main-openapi"))
    implementation(project(":ok-cryptoMarket-mappers"))

    // Stubs
    implementation(project(":ok-cryptoMarket-stubs"))

    // tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("com.ninja-squad:springmockk:3.0.1") // mockking beans
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}