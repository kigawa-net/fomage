plugins {
    `kotlin-dsl`
}
fun DependencyHandler.plugin(pluginName: String, version: String) {
    implementation("$pluginName:$pluginName.gradle.plugin:$version")
}

dependencies {
    plugin("org.jetbrains.kotlin.jvm", "2.1.0")
}