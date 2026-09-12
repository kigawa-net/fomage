package net.kigawa.fomage.core.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint

/**
 * Security configuration for the Fomage application.
 * 認証はKeycloak(OIDC)を使用する。
 */
@Configuration
@EnableWebSecurity
open class SecurityConfig(
    private val env: Environment,
    private val clientRegistrationRepository: ClientRegistrationRepository,
) {

    /**
     * Configures the security filter chain.
     */
    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val securityEnabled = env.getProperty("SECURITY_ENABLED", "true").toBoolean()

        if (!securityEnabled) {
            http.csrf { it.disable() }
                .authorizeHttpRequests { it.anyRequest().permitAll() }
            return http.build()
        }

        http.csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/favicon.ico").permitAll()
                    .requestMatchers("/error", "/health").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().authenticated()
            }
            // 未認証アクセスはKeycloakの認可エンドポイントへ直接リダイレクトする
            // (認証プロバイダはKeycloak1つのみのため、選択画面は不要)
            .exceptionHandling { it.authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/oauth2/authorization/keycloak")) }
            .oauth2Login {}
            .logout { logout ->
                logout
                    .logoutSuccessHandler(oidcLogoutSuccessHandler())
                    .permitAll()
            }

        return http.build()
    }

    /**
     * ログアウト時にKeycloak側のセッションも終了させる(RP-Initiated Logout)。
     */
    private fun oidcLogoutSuccessHandler(): OidcClientInitiatedLogoutSuccessHandler {
        val handler = OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository)
        handler.setPostLogoutRedirectUri("{baseUrl}/")
        return handler
    }

    /**
     * Password encoder bean.
     * (fonsoleのUserドキュメント管理機能[UserService]が利用するため維持)
     */
    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
