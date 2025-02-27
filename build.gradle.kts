plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "fr.univtln.bruno.samples.network"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("fr.univtln.bruno.samples.network.MainBisKt")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "fr.univtln.bruno.samples.network.MainBisKt"
        )
    }
}