package ar.edu.unsam.pds.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class FilterChainConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.addFilterAfter(MyTempCorsFilter(), DigestAuthenticationFilter::class.java)

        http.authorizeHttpRequests { authorize -> authorize
            // #########################################################################################################
            // # all user                                                                                              #
            // #########################################################################################################
            .requestMatchers(
                AntPathRequestMatcher.antMatcher(HttpMethod.OPTIONS, "/**"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/media/public/*"),

                // registration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/registration/login"),

                // public @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/assignments/"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/assignments/course/*"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/assignments/*"),

                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/courses/"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/courses/institution/*"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/courses/*"),

                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/institutions/"),
                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/institutions/*"),

                AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/users/"),
            ).permitAll()
            .anyRequest().authenticated()
        }

        http.formLogin { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }

        http.headers { h -> h.frameOptions { it.disable() } }

        return http.build()
    }
}