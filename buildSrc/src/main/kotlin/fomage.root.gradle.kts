plugins {
    kotlin("jvm") apply false
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
//group = "net.kigawa"
//version = "1.0.0"
//allprojects {
//    group = "net.kigawa"
//    version = "1.0.0"
//    plugins {
//        id("fomage.common")
//    }
//}
//repositories {
//    mavenCentral()
//}