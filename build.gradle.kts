
plugins {
    kotlin("jvm") apply false
    //kotlin("multiplatform") apply false
}

group = "ru.otus.otuskotlin.cryptomarket"
version = "0.0.1"

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

//tasks.withType<KotlinCompile>() {
//    kotlinOptions.jvmTarget = "11"
//}