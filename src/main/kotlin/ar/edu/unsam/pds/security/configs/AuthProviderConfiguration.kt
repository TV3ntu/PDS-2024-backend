package ar.edu.unsam.pds.security.configs

import ar.edu.unsam.pds.security.services.AppUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthProviderConfiguration {
    @Autowired private lateinit var passwordEncoder: PasswordEncoder
    @Autowired private lateinit var userService: AppUserDetailsService

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        return DaoAuthenticationProvider().apply {
            setUserDetailsService(userService)
            setPasswordEncoder(passwordEncoder)
        }
    }
}