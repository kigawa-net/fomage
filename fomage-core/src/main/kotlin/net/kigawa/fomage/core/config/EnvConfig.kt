package net.kigawa.fomage.core.config

import io.github.cdimascio.dotenv.dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import jakarta.annotation.PostConstruct

/**
 * Environment configuration for the Fomage application.
 * This class loads environment variables from .env file and makes them available to the application.
 */
@Configuration
open class EnvConfig(private val environment: ConfigurableEnvironment) {

    private val dotenv by lazy {
        dotenv {
            // Look for .env file in the current directory and parent directories
            directory = "./"
            // If .env file is not found, don't throw an exception
            ignoreIfMissing = true
        }
    }

    /**
     * Initializes the environment by loading variables from .env file.
     */
    @PostConstruct
    fun init() {
        val envVars = dotenv.entries().associate { it.key to it.value }
        val propertySource = MapPropertySource("dotenvProperties", envVars)
        environment.propertySources.addFirst(propertySource)
    }

    /**
     * Provides the Dotenv instance as a bean for other components to use if needed.
     */
    @Bean
    open fun dotenv() = dotenv
}
