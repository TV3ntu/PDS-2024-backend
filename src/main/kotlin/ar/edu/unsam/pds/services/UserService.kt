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
import org.springframework.security.core.Authentication
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
    private val institutionService: InstitutionService,
    private val emailService: EmailService,
    private val storageService: StorageService
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        return principalRepository.findUserByEmail(email).orElseThrow {
            UsernameNotFoundException("El usuario no existe.")
        }
    }

    fun login(user: LoginForm, request: HttpServletRequest): UserDetailResponseDto {
        try {
            request.login(user.email, user.password)
        } catch (e: ServletException) {
            throw NotFoundException("Usuario y/o contraseña invalidos.")
        }

        val principal = (request.userPrincipal as Authentication).principal as Principal
        val principalUser = principal.getUser()
        val nextClass = getSubscriptions(principalUser.id.toString()).firstOrNull()

        return UserMapper.buildUserDetailDto(principalUser,nextClass)
    }

    @Transactional
    fun register(form: RegisterFormDto): UserResponseDto {
        // Verificar si el correo ya está en uso
        if (principalRepository.findUserByEmail(form.email).isPresent) {
            throw InternalServerError("El correo ya está en uso.")
        }
        val encryptedPassword = encryptPassword(form.password)

        // Crear y guardar el nuevo usuario
        val newUser = User(
            name = form.name,
            lastName = form.lastName,
            email = form.email,
            image = storageService.defaultImage
        )
        userRepository.save(newUser)

        // Crear y guardar el principal asociado
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
            val imageName = storageService.updatePrivate(user.image,userDetail.file)

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

        if(avatar!=storageService.defaultImage){
            storageService.deletePrivate(principal.getUser().image)
        }
    }
}