plugins {
    id("fomage.root")
}

// Apply Java plugin to all subprojects to ensure sourceSets is available
subprojects {
    apply(plugin = "java")
}

allprojects {
    repositories {
        mavenCentral()
    }
}

