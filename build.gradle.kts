import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    //trick: for the same plugin versions in all sub-modules
//    alias(libs.plugins.kotlin.jvm) apply false
//    alias(libs.plugins.shadow) apply false
//    alias(libs.plugins.dokka).apply(false)
//    alias(libs.plugins.vanniktech.maven.publish).apply(false)
    id("fomage.root")
}
//dependencies {
//    implementation(libs.mongodb.driver)
//    implementation(libs.mongodb.bson)
//    implementation(libs.dotenv)
//    implementation(libs.kotlinx.coroutines.core)
//    implementation(libs.logback)
//}