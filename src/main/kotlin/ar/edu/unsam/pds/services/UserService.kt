package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    private var users = UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return users.findByUsername(username).orElseThrow {
            UsernameNotFoundException("usuario inexistente")
        }
    }

    fun getUserAll(): List<User> {
        return users.getAll().toList()
    }
}