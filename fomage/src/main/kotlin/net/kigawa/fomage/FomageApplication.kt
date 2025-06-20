package net.kigawa.fomage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/**
 * Main entry point for the Fomage application.
 * Fomage is a web-based management tool for MongoDB databases used by the fonsole application.
 */
@SpringBootApplication
@ComponentScan(basePackages = [
    "net.kigawa.fomage",
    "net.kigawa.fomage.web",
    "net.kigawa.fomage.api",
    "net.kigawa.fomage.core"
])
open class FomageApplication

/**
 * Main function to start the Spring Boot application.
 */
fun main(args: Array<String>) {
    runApplication<FomageApplication>(*args)
}
