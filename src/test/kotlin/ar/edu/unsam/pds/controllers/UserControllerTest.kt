package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserControllerTest {
    @Mock
    private lateinit var userService: UserService
    private lateinit var userController: UserController

    private lateinit var user: User
    private lateinit var userReq: UserRequestDto
    private lateinit var userRes: UserResponseDto
    private lateinit var uuid: String

    @BeforeEach
    fun setUp() {
        userController = UserController()
        userController.userService = userService

        user = User(
            name = "Juan",
            lastName = "Perez",
            email = "juan_perezm@email.com",
            image = ""
        ).apply {
            id = UUID.randomUUID()
        }

        uuid = user.id.toString()

        userReq = UserRequestDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image,
            isAdmin = true,
            credits = 1000.0,
            id = user.id.toString(),
            nextClass = "nextClass"
        )

        userRes = UserResponseDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image,
            id = user.id.toString(),
            credits = 1000.0,
            isAdmin = true
        )
    }

    @Test
    fun `test get all user`() {
        val users = listOf(UserMapper.buildUserDto(user))

        `when`(userService.getUserAll()).thenReturn(users)

        val responseEntity = userController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == users)
    }

    @Test
    fun `test login user`() {
        val request = MockHttpServletRequest()
        val userForm = LoginForm(user.email, "666")
        val nextClass: SubscriptionResponseDto? = null

        `when`(userService.login(userForm, request)).thenReturn(
            UserMapper.buildUserDetailDto(user,nextClass)
        )

        val responseEntity = userController.login(userForm, request)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == UserMapper.buildUserDetailDto(user,nextClass))
    }

    @Test
    fun `test logout user`() {
        val request = MockHttpServletRequest()
        request.setParameter("logout", "true")

        val responseEntity = userController.logout(request)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == mapOf("message" to "Se ha deslogeado correctamente."))
    }

    @Test
    fun `test register a particular user`() {
        val expectedValue = UserMapper.buildUserDto(user)
        val userRegister = RegisterFormDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            password = "666"
        )

        `when`(userService.register(userRegister)).thenReturn(expectedValue)

        val responseEntity = userController.register(userRegister)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == expectedValue)
    }

    @Test
    fun `test get a particular user`() {
        val user = UserMapper.buildUserDetailDto(user, null)

        `when`(userService.getUserDetail(uuid)).thenReturn(user)

        val responseEntity = userController.userItem(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == user)
    }

    @Test
    fun `test update a particular user`() {
        `when`(userService.updateDetail(uuid, userReq)).thenReturn(userRes)

        val responseEntity = userController.updateDetail(uuid, userReq)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == userRes)
    }

    @Test
    fun `test update a particular user with less fields`() {
        `when`(userService.updateDetail(uuid, userReq)).thenReturn(userRes)

        val responseEntity = userController.updateDetail(uuid, userReq)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == userRes)
        assert(responseEntity.body?.credits == 1000.0)
    }

    @Test
    fun `test get subscribed courses`() {
        val subscribedCourses = listOf(
            CourseResponseDto(
                id = "123",
                title = "title",
                description = "description",
                category = "category",
                image = "image"
            )
        )

        `when`(userService.getSubscribedCourses("123")).thenReturn(subscribedCourses)

        val responseEntity = userController.getSubscribedCourses("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == subscribedCourses)
    }

    @Test
    fun `test get subscribed`() {
        val subscriptions = listOf(
            SubscriptionResponseDto(
                institutionName = "institutionName",
                courseId = "courseId",
                courseName = "courseName",
                date = "date",
                hour = "hour",
                status = "status",
                assignmentId = "assignmentId"
            )
        )

        `when`(userService.getSubscriptions("123")).thenReturn(subscriptions)

        val responseEntity = userController.getSubscriptions("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == subscriptions)
    }
}