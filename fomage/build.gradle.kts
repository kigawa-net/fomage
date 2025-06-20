plugins {
    id("fomage.app")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
}

repositories {
    mavenCentral()
}

dependencies {
    // Module dependencies
    implementation(project(":fomage-api"))
    implementation(project(":fomage-core"))
    implementation(project(":fomage-web"))

    // Kotlin standard library and coroutines
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)

    // Spring Boot
    implementation(libs.spring.boot.starter)

    // MongoDB
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson)

    // Environment configuration
    implementation(libs.dotenv)

    // Logging
    implementation(libs.logback)
    implementation(libs.logback.core)

    // Testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.spring.boot.starter.test)
}

application {
    mainClass.set("net.kigawa.fomage.FomageApplicationKt")
}
