plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":ok-cryptomarket-common"))
    implementation(project(":ok-cryptomarket-transport-main-openapi"))
    implementation("junit:junit:4.13.1")

}
