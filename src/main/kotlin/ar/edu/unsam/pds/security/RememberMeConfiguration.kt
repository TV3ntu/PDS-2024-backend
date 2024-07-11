package ar.edu.unsam.pds.security

import ar.edu.unsam.pds.security.services.AppUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices

@Configuration
class RememberMeConfiguration {
    @Autowired private lateinit var userService: AppUserDetailsService

    @Bean
    fun rememberMeServices(): TokenBasedRememberMeServices {
        return TokenBasedRememberMeServices("springRocks", userService).apply {
            setAlwaysRemember(true)
        }
    }
}