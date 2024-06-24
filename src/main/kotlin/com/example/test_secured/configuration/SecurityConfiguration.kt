package com.example.test_secured.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http() {
            authorizeHttpRequests {
                authorize("/articles/create", authenticated)
                authorize(anyRequest, permitAll)
            }
            formLogin { defaultSuccessUrl("/", true) }
            httpBasic { }
            headers { frameOptions { disable() } }
            csrf { ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")) }
        }

        return http.build()
    }
}
