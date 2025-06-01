plugins {
    `kotlin-dsl`
}

//val kotlinVersion = "2.1.10"
fun pluginId(pluginName: String, version: String) = "$pluginName:$pluginName.gradle.plugin:$version"
//fun kotlinPluginId(pluginName: String) = pluginId("org.jetbrains.kotlin.$pluginName", kotlinVersion)
//fun kotlinId(id: String) = "org.jetbrains.kotlin:$id:$kotlinVersion"
dependencies {
//    implementation(kotlinPluginId("jvm"))
    implementation(pluginId("org.jetbrains.kotlin.jvm", "2.1.10"))
}
repositories {
    mavenCentral()
}