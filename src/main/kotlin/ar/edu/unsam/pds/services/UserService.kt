package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService /* : UserDetailsService*/ {
    private var users = UserRepository

    fun loadUserByUsername(username: String): Any?/*UserDetails*/ {
//        return users.findByUsername(username).orElseThrow {
//            UsernameNotFoundException("non-existent user")
//        }

        return null;
    }

    fun getUserAll(): List<User> {
        return users.getAll().toList()
    }
}