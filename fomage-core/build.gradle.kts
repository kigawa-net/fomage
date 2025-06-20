plugins {
    id("fomage.common")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(libs.kotlin.stdlib)

    // Spring Boot
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.data.mongodb)
    implementation(libs.spring.boot.starter.security)

    // MongoDB
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson)

    // Environment configuration
    implementation(libs.dotenv)

    // Testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.spring.boot.starter.test)
}
