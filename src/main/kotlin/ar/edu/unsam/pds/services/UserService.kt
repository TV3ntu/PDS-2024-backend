package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.response.UserDetailDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.utils.Mapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return userRepository.findByUsername(email).orElseThrow {
            UsernameNotFoundException("El usuario no existe.")
        }
    }

    fun login(user: LoginForm, request: HttpServletRequest): UserResponseDto {
        try {
            request.login(user.email, user.password)
        } catch (e: ServletException) {
            throw NotFoundException("Usuario y/o contraseña invalidos.")
        }

        val principal = (request.userPrincipal as Authentication).principal as Principal
        val principalUser = principal.user ?: throw InternalServerError("Internal Server Error")

        return UserResponseDto(
            principalUser.name,
            principalUser.lastName,
            principalUser.email,
            principalUser.image
        )
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.getAll()
        return user.map { Mapper.buildUserDto(it) }
    }

    fun getUserItem(idUser: String): UserResponseDto {
        val user = userRepository.findById(idUser) as User
        return buildUserDto(user)
    }

    fun getDetail(idUser: String): UserDetailDto {
        val user = userRepository.findById(idUser) as User
        return UserDetailDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image
        )
    }

    fun updateDetail(idUser: String, userDetail: UserDetailDto): UserDetailDto {
        val user = userRepository.findById(idUser) as User
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