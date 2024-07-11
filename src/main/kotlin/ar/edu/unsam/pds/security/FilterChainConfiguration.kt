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
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher

@Configuration
@EnableWebSecurity
class FilterChainConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity, rememberMeServices: TokenBasedRememberMeServices): SecurityFilterChain {
        // #############################################################################################################
        // # filters for exceptions                                                                                    #
        // #############################################################################################################
        http.addFilterAfter(MyTempCorsFilter(), DigestAuthenticationFilter::class.java)

//        http.sessionManagement { sm ->
//            sm.sessionConcurrency { sc ->
//                sc.maximumSessions(10)
//                  .expiredUrl("http://localhost:4200/ingresar")
//            }
//               .invalidSessionUrl("http://localhost:4200/ingresar")
//        }

        // #############################################################################################################
        // # all request matchers                                                                                      #
        // #############################################################################################################
        http.authorizeHttpRequests { authorize -> authorize
            // #########################################################################################################
            // # dispatcher for exceptions                                                                             #
            // #########################################################################################################
            .dispatcherTypeMatchers(ERROR, ASYNC).permitAll()

            // ADMIN @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(GET, "/api/institutions/admin/**"),
                antMatcher(GET, "/api/courses/admin/**"),
                antMatcher(POST, "/api/courses"),
                antMatcher(POST, "/api/courses/**"),
                antMatcher(DELETE, "/api/courses/**"),
                antMatcher(DELETE, "/api/courses"),
                antMatcher(POST, "/api/assignments"),
                antMatcher(DELETE, "/api/assignments/**"),
                antMatcher(DELETE, "/api/institutions/**"),
                antMatcher(GET, "/api/assignments/*/admin"),
            ).hasRole("ADMIN")

            // USER @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(DELETE, "/api/users"),
            ).hasRole("USER")

            // USER Y ADMIN @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                antMatcher(POST, "/api/institutions"),
                antMatcher(POST, "/api/assignments/subscribe"),
                antMatcher(POST, "/api/assignments/unsubscribe"),
                antMatcher(PATCH, "/api/users/**"),

                antMatcher(GET, "/api/users/reviews"),
                antMatcher(POST, "/api/courses/*/review"),
            ).hasAnyRole("USER", "ADMIN")

            // #########################################################################################################
            // # all user                                                                                              #
            // #########################################################################################################
            .requestMatchers(
                antMatcher(OPTIONS, "/**"),
                antMatcher(GET, "/media/public/**"),

                // swagger @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher("/swagger-ui/**"),
                antMatcher("/v3/api-docs/**"),

                // registration @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(POST, "/api/users/login"),
                antMatcher(POST, "/api/users/register"),

                // public @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                antMatcher(GET, "/api/institutions"),
                antMatcher(GET, "/api/institutions/**"),
                antMatcher(GET, "/api/courses"),
                antMatcher(GET, "/api/courses/**"),
                antMatcher(GET, "/api/courses/*/stats"),
                antMatcher(GET, "/api/assignments"),
                antMatcher(GET, "/api/assignments/**"),
                antMatcher(GET, "/api/users"),
                antMatcher(GET, "/api/users/*"),
                antMatcher(GET, "/api/users/*/courses"),
                antMatcher(GET, "/api/users/*/subscriptions"),

                antMatcher(GET, "/api/courses/*/reviews"),
            ).permitAll()

            // H2 DataBase @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .requestMatchers(
                PathRequest.toH2Console()
            ).permitAll()

            // the rest of the endpoints @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            .anyRequest().authenticated()
        }

        http.rememberMe { remember -> remember
            .rememberMeServices(rememberMeServices)
        }

        http.formLogin { it.disable() }
        http.cors { it.disable() }
        http.csrf { it.disable() }

        http.headers { h -> h.frameOptions { it.disable() } }

        return http.build()
    }
}