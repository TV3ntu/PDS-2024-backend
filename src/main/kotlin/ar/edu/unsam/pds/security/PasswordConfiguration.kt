package ar.edu.unsam.pds.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class PasswordConfiguration {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(10)

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return passwordEncoder
    }
}