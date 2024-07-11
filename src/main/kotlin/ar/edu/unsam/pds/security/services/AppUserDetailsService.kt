package ar.edu.unsam.pds.security.services

import ar.edu.unsam.pds.security.repository.PrincipalRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AppUserDetailsService(
    private val principalRepository: PrincipalRepository,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return principalRepository.findUserByEmail(email).orElseThrow {
            UsernameNotFoundException("El usuario no existe.")
        }
    }
}