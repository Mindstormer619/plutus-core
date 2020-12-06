import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.darthsid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.3-jdk8")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}