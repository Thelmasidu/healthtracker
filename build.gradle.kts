plugins {
    kotlin("jvm") version "2.0.10"
    // Plugin for Dokka - KDoc generating tool
    id("org.jetbrains.dokka") version "1.9.20"
}


group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(16)
}

