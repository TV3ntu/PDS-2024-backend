package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {
    private var userRepository = UserRepository

    fun loadUserByUsername(username: String): Any?/*UserDetails*/ {
//        return users.findByUsername(username).orElseThrow {
//            UsernameNotFoundException("non-existent user")
//        }

        return null;
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.getAll()
        return user.map { buildUserDto(it) }
    }

    private fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(user.name, user.lastName, user.email, user.image)
    }

}