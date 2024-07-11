package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestUpdateDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.mappers.CourseMapper
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val principalRepository: PrincipalRepository,
    private val institutionService: InstitutionService,

    private val emailService: EmailService,
    private val storageService: StorageService,
    private val rememberMeServices: TokenBasedRememberMeServices
) {

    fun login(user: LoginForm, request: HttpServletRequest, response: HttpServletResponse): UserDetailResponseDto {
        try {
            request.login(user.email, user.password)
        } catch (e: ServletException) {
            throw NotFoundException("Usuario y/o contraseña invalidos.")
        }

        val auth: Authentication = request.userPrincipal as Authentication

        if (user.rememberMe) {
            rememberMeServices.loginSuccess(request, response, auth)
        }

        val principalUser = (auth.principal as Principal).getUser()
        val nextClass = getSubscriptions(principalUser.id.toString()).firstOrNull()

        return UserMapper.buildUserDetailDto(principalUser, nextClass)
    }

    @Transactional
    fun register(form: RegisterFormDto): UserResponseDto {
        if (principalRepository.findUserByEmail(form.email).isPresent) {
            throw InternalServerError("El correo ya está en uso.")
        }
        val encryptedPassword = encryptPassword(form.password)

        val newUser = User(
            name = form.name,
            lastName = form.lastName,
            email = form.email,
            image = storageService.defaultImage
        )
        userRepository.save(newUser)

        val principal = Principal().apply {
            username = form.email
            password = encryptedPassword
            initProperties()
            user = newUser
        }
        principalRepository.save(principal)

        return UserMapper.buildUserDto(newUser)
    }

    private fun encryptPassword(password: String): String {
        val encoder = BCryptPasswordEncoder()
        return encoder.encode(password)
    }

    fun getUserAll(): List<UserResponseDto> {
        val user = userRepository.findAll()
        return user.map { UserMapper.buildUserDto(it) }
    }

    fun getUserDetail(idUser: String): UserDetailResponseDto {
        val user = findUserById(idUser)
        val nextClass = getSubscriptions(idUser).firstOrNull()
        return UserMapper.buildUserDetailDto(user, nextClass)
    }

    @Transactional
    fun updateDetail(idUser: String, userDetail: UserRequestUpdateDto): UserResponseDto {
        val user = findUserById(idUser)

        if (userDetail.file != null) {
            val imageName = storageService.updatePrivate(user.image, userDetail.file)

            user.image = imageName
        }

        user.name = userDetail.name
        user.lastName = userDetail.lastName
        user.email = userDetail.email

        if (user.credits < userDetail.credits) {
            emailService.sendCreditsLoadedEmail(user.email, userDetail.credits, user.name)
        }
        user.credits = userDetail.credits //?: user.credits
        userRepository.save(user)
        return UserMapper.buildUserDto(user)
    }

    fun getSubscribedCourses(idUser: String): List<CourseResponseDto> {
        val user = findUserById(idUser)
        return user.subscribedCourses().map { CourseMapper.buildCourseDto(it) }
    }

    private fun findUserById(idUser: String): User {
        val uuid = UUID.fromString(idUser)
        return userRepository.findById(uuid).orElseThrow {
            NotFoundException("Usuario no encontrado para el uuid suministrado")
        }
    }

    fun getSubscriptions(idUser: String): List<SubscriptionResponseDto> {
        val user = findUserById(idUser)
        val subscriptions = user.assignmentsList.map { assignment ->
            val institution = institutionService.findInstitutionByCourseId(assignment.course.id)
            AssignmentMapper.buildSubscriptionDto(assignment, institution)
        }
        return orderSubscriptionsByDate(subscriptions)
    }

    private fun orderSubscriptionsByDate(subscriptions: List<SubscriptionResponseDto>): List<SubscriptionResponseDto> {
        return subscriptions.sortedBy { it.date }
    }

    @Transactional
    fun deleteAccount(principal: Principal, request: HttpServletRequest) {
        if (userRepository.hasInscriptions(principal.getUser().id)) {
            throw NotFoundException("No se puede eliminar un usuario que esta inscripto a un curso.")
        }
        val avatar = principal.getUser().image

        request.logout()

        userRepository.delete(principal.user!!)
        principalRepository.delete(principal)

        if (avatar != storageService.defaultImage) {
            storageService.deletePrivate(principal.getUser().image)
        }
    }
}