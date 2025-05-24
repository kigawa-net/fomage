import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
//    alias(libs.plugins.dokka).apply(false)
//    alias(libs.plugins.vanniktech.maven.publish).apply(false)
    id("root")
}
repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.mongodb.driver)
    implementation(libs.mongodb.bson)
    implementation(libs.dotenv)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.logback)
}