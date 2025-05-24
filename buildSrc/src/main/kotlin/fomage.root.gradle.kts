plugins {
    kotlin("jvm")
    application
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
application {
    mainClass.set("net.kigawa.fomage.Main")
}