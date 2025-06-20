plugins {
    id("fomage.common")
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin standard library
    implementation(libs.kotlin.stdlib)
    
    // Testing
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.test.junit)
}