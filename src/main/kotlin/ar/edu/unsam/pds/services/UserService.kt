package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
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
        return Mapper.buildUserDto(principalUser)
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.getAll()
        return user.map { Mapper.buildUserDto(it) }
    }

    fun getUserItem(idUser: String): UserResponseDto {
        val user = userRepository.findById(idUser) as User
        return Mapper.buildUserDto(user)
    }

    fun updateDetail(idUser: String, userDetail: UserResponseDto): UserResponseDto {
        val user = userRepository.findById(idUser) as User
        val updatedUser = patchUser(user, userDetail)
        userRepository.update(idUser, updatedUser)
        return Mapper.buildUserDto(user)
    }

    private fun patchUser(user: User, userDetail: UserResponseDto): User {
        userDetail.name.let { user.name = it }
        userDetail.lastName.let { user.lastName = it }
        userDetail.email.let { user.email = it }
        userDetail.image.let { user.image = it }
        return user
    }

}