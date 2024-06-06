package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.services.UserService
import ar.edu.unsam.pds.utils.Mapper
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
    }

    @Test
    fun `test get all user`() {
        val users = listOf(Mapper.buildUserDto(user))

        `when`(userService.getUserAll()).thenReturn(users)

        val responseEntity = userController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == users)
    }

    @Test
    fun `test login user`() {
        val request = MockHttpServletRequest()
        val userForm = LoginForm(user.email, "666")

        `when`(userService.login(userForm, request)).thenReturn(
            Mapper.buildUserDto(user)
        )

        val responseEntity = userController.login(userForm, request)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == Mapper.buildUserDto(user))
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
        val expectedValue = Mapper.buildUserDto(user)
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
        val user = Mapper.buildUserDetailDto(user, null)

        `when`(userService.getUserDetail(uuid)).thenReturn(user)

        val responseEntity = userController.userItem(uuid)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == user)
    }

    @Test
    fun `test update a particular user`() {
        val user = Mapper.buildUserDto(user)

        `when`(userService.updateDetail(uuid, user)).thenReturn(user)

        val responseEntity = userController.updateDetail(uuid, user)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == user)
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
                status = "status"
            )
        )

        `when`(userService.getSubscriptions("123")).thenReturn(subscriptions)

        val responseEntity = userController.getSubscriptions("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == subscriptions)
    }
}