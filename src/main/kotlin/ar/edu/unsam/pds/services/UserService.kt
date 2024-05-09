package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.utils.Mapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByUsername(email).orElseThrow {
            UsernameNotFoundException("El usuario no existe.")
        }
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.getAll()
        return user.map { Mapper.buildUserDto(it) }
    }
}