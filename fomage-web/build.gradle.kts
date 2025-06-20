plugins {
    id("fomage.common")
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management)
}

repositories {
    mavenCentral()
}

dependencies {
    // Module dependencies
    implementation(project(":fomage-api"))
    implementation(project(":fomage-core"))

    // Kotlin standard library
    implementation(libs.kotlin.stdlib)

    // Spring Boot
    implementation(libs.spring.boot.starter)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.thymeleaf.extras.springsecurity)

    // Testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.spring.boot.starter.test)
}
