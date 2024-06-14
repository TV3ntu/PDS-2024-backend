package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.utils.Mapper
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val principalRepository: PrincipalRepository,
    private val institutionService: InstitutionService

) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return principalRepository.findUserByEmail(email).orElseThrow {
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

    @Transactional
    fun register(form: RegisterFormDto): UserResponseDto {
        if (principalRepository.findUserByEmail(form.email).isPresent) {
            throw InternalServerError("El correo ya está en uso.")
        }

        val encoder = BCryptPasswordEncoder()
        val encryptedPassword = encoder.encode(form.password)

        val newUser = User(
            name = form.name,
            lastName = form.lastName,
            email = form.email,
            image = ""
        )
        userRepository.save(newUser)

        val principal = Principal().apply {
            setUsername(form.email)
            setPassword(encryptedPassword)
            initProperties()
            user = newUser
        }
        principalRepository.save(principal)
        
        return Mapper.buildUserDto(newUser)
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.findAll()
        return user.map { Mapper.buildUserDto(it) }
    }

    fun getUserDetail(idUser: String): UserDetailResponseDto {
        val user = findUserById(idUser)
        val nextClass = getSubscriptions(idUser).firstOrNull()
        return Mapper.buildUserDetailDto(user, nextClass)
    }

    @Transactional
    fun updateDetail(idUser: String, userDetail: UserResponseDto): UserResponseDto {
        val user = findUserById(idUser)
        val updatedUser = Mapper.patchUser(user, userDetail)
        userRepository.save(updatedUser)
        return Mapper.buildUserDto(user)
    }

    fun getSubscribedCourses(idUser: String): List<CourseResponseDto> {
        val user = findUserById(idUser)
        return user.subscribedCourses().map { Mapper.buildCourseDto(it) }
    }

    private fun findUserById(idUser: String): User {
        val uuid = UUID.fromString(idUser)
        return userRepository.findById(uuid).orElseThrow {
            NotFoundException("Usuario no encontrado")
        }
    }

    fun getSubscriptions(idUser: String): List<SubscriptionResponseDto> {
        val user = findUserById(idUser)
        val subscriptions = user.assignmentsList.map { assignment ->
            val institution = institutionService.findInstitutionByCourseId(assignment.course.id)
            Mapper.buildSubscriptionDto(assignment, institution)
        }
        return orderSubscriptions(subscriptions)
    }

    private fun orderSubscriptions(subscriptions: List<SubscriptionResponseDto>): List<SubscriptionResponseDto> {
        return subscriptions.sortedBy { it.date }
    }
}