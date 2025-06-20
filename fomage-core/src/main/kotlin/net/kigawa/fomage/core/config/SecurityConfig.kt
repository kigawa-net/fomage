package net.kigawa.fomage.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.core.env.Environment

/**
 * Security configuration for the Fomage application.
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig(private val env: Environment) {

    /**
     * Configures the security filter chain.
     */
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val securityEnabled = env.getProperty("SECURITY_ENABLED", "true").toBoolean()
        val apiKey = env.getProperty("API_KEY_SECRET", "default-secret-key")

        if (!securityEnabled) {
            http.csrf { it.disable() }
                .authorizeHttpRequests { it.anyRequest().permitAll() }
            return http.build()
        }

        http.csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
            }
            .httpBasic {}

        return http.build()
    }
}
