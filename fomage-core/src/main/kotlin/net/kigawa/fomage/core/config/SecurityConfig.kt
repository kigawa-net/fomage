package net.kigawa.fomage.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.beans.factory.annotation.Value

/**
 * Security configuration for the Fomage application.
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig {

    @Value("\${fomage.security.enabled:true}")
    private var securityEnabled: Boolean = true

    @Value("\${fomage.security.api-key:default-key}")
    private lateinit var apiKey: String

    /**
     * Configures the security filter chain.
     */
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
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