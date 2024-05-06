package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService /* : UserDetailsService*/ {
    private var user = UserRepository

    fun loadUserByUsername(username: String): Any?/*UserDetails*/ {
//        return user.findByUsername(username).orElseThrow {
//            UsernameNotFoundException("non-existent user")
//        }

        return null;
    }

}