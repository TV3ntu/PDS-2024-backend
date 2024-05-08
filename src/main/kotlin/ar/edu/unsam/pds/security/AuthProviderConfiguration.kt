package ar.edu.unsam.pds.security

import ar.edu.unsam.pds.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthProviderConfiguration {
    @Autowired private lateinit var passwordEncoder: PasswordEncoder
    @Autowired private lateinit var userService: UserService

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()

        daoAuthenticationProvider.setUserDetailsService(userService)
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder)

        return daoAuthenticationProvider
    }
}