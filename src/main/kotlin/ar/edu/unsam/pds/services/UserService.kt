package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.response.UserDetailDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService : UserDetailsService {
    private var userRepository = UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username).orElseThrow {
            UsernameNotFoundException("usuario inexistente")
        }
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.getAll()
        return user.map { buildUserDto(it) }
    }

    fun getUserItem(idUser: String): UserResponseDto {
        val user = userRepository.findByObjectId(idUser) as User
        return buildUserDto(user)
    }

    fun getDetail(idUser: String): UserDetailDto {
        val user = userRepository.findByObjectId(idUser) as User
        return UserDetailDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image
        )
    }

    fun updateDetail(idUser: String, userDetail: UserDetailDto): UserDetailDto {
        val user = userRepository.findByObjectId(idUser) as User
    val updatedUser = patchUser(user, userDetail)

        userRepository.update(idUser, updatedUser)
        return UserDetailDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image
        )
    }

    private fun patchUser(user: User, userDetail: UserDetailDto): User {
        userDetail.name.let { user.name = it }
        userDetail.lastName.let { user.lastName = it }
        userDetail.email.let { user.email = it }
        userDetail.image.let { user.image = it }
        return user
    }

    private fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(user.name, user.lastName, user.email, user.image, user.id)
    }

}