import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories

val kotlinVersion = "2.2.21"
val junitVersion = "6.0.3"
val assertjVersion = "3.27.7"

plugins {
    kotlin("jvm") version "2.2.21"
}

java.sourceCompatibility = JavaVersion.VERSION_24
java.targetCompatibility = JavaVersion.VERSION_24

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
