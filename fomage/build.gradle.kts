plugins {
    id("fomage.app")
}

repositories {
    mavenCentral()
}

dependencies {
    // Module dependencies
    implementation(project(":fomage-api"))

    // Kotlin standard library and coroutines
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)

    // MongoDB
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson)

    // Environment configuration
    implementation(libs.dotenv)

    // Logging
    implementation(libs.logback)

    // Testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.kotlinx.coroutines.test)
}

application {
    mainClass.set("net.kigawa.fomage.Main")
}
