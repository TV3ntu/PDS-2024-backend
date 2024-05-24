package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.AssignmentRepository
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
import java.time.LocalDate

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
        val user = findUserById(idUser)
        return Mapper.buildUserDto(user)
    }

    fun updateDetail(idUser: String, userDetail: UserResponseDto): UserResponseDto {
        val user = findUserById(idUser)
        val updatedUser = Mapper.patchUser(user, userDetail)
        userRepository.update(idUser, updatedUser)
        return Mapper.buildUserDto(user)
    }

    private fun findUserById(idUser: String): User {
        return userRepository.findById(idUser).orElseThrow { NotFoundException("Usuario no encontrado") }
    }

    fun getSubscribedCourses(idUser: String): List<CourseResponseDto>? {
        return userRepository.findById(idUser).subscribedCourses()
            .takeIf { it.isNotEmpty() }
            ?.map { Mapper.buildCourseDto(it) }
            ?: throw NotFoundException("El usuario no tiene cursos inscriptos.")
    }
}