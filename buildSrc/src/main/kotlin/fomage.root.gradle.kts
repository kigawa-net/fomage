import gradle.kotlin.dsl.accessors._8afd1ed6040836139df3e5fc6cc50a92.application

plugins {
    kotlin("jvm")
}
object Conf {
    const val GROUP = "net.kigawa"
    const val VERSION = "1.0.0"
}

group = Conf.GROUP
version = Conf.VERSION
allprojects {
    group = Conf.GROUP
    version = Conf.VERSION
}
