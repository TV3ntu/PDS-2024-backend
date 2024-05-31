package ar.edu.unsam.pds.security

import jakarta.servlet.DispatcherType.ASYNC
import jakarta.servlet.DispatcherType.ERROR
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class FilterChainConfiguration {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.addFilterAfter(MyTempCorsFilter(), DigestAuthenticationFilter::class.java)

        http.authorizeHttpRequests { authorize -> authorize
            // #########################################################################################################
            // # dispatcher for exceptions                                                                             #
            // #########################################################################################################
            .dispatcherTypeMatchers(ERROR, ASYNC).permitAll()

            // #########################################################################################################
            // # all user                                                                                              #
            // #########################################################################################################
            .requestMatchers(
                antMatcher(OPTIONS, "/**"),
                antMatcher(GET, "/media/public/*"),

                // swagger @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher("/swagger-ui/**"),
                antMatcher("/v3/api-docs/**"),

                // registration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(POST, "/api/users/login"),

                // public @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(GET, "/api/institutions"),
                antMatcher(GET, "/api/institutions/*"),

                antMatcher(GET, "/api/courses"),
                antMatcher(GET, "/api/courses/*"),

                antMatcher(GET, "/api/assignments"),
                antMatcher(GET, "/api/assignments/*"),

                antMatcher(POST, "/api/assignments/subscribe"),
                antMatcher(PATCH, "/api/assignments/unsubscribe"),

                antMatcher(GET, "/api/users"),
                antMatcher(GET, "/api/users/*"),
                antMatcher(PATCH, "/api/users/*"),
                antMatcher(GET, "/api/users/*/courses"),
            ).permitAll()

            // automatically generated endpoints @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(POST, "/api/assignments"),
                antMatcher(PUT, "/api/assignments/*"),
                antMatcher(DELETE, "/api/assignments/*"),
                antMatcher(PATCH, "/api/assignments/*"),

                antMatcher(POST, "/api/courses"),
                antMatcher(PUT, "/api/courses/*"),
                antMatcher(DELETE, "/api/courses/*"),
                antMatcher(PATCH, "/api/courses/*"),

                antMatcher(POST, "/api/institutions"),
                antMatcher(PUT, "/api/institutions/*"),
                antMatcher(DELETE, "/api/institutions/*"),
                antMatcher(PATCH, "/api/institutions/*"),

                antMatcher(POST, "/api/users"),
                antMatcher(PUT, "/api/users/*"),
                antMatcher(DELETE, "/api/users/*"),
            ).permitAll()

            // H2 DataBase @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                PathRequest.toH2Console()
            ).permitAll()

            // the rest of the endpoints @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .anyRequest().authenticated()
        }

        http.formLogin { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }

        http.headers { h -> h.frameOptions { it.disable() } }

        return http.build()
    }
}