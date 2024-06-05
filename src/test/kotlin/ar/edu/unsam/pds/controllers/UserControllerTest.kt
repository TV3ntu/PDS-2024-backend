package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus

@ExtendWith(MockitoExtension::class)
class UserControllerTest {
    @Mock
    private lateinit var userService: UserService
    private lateinit var userController: UserController

    @BeforeEach
    fun setUp() {
        userController = UserController()
        userController.userService = userService
    }

    @Test
    fun `test get all user`() {
        val users = listOf(
            UserResponseDto(
                id = "id",
                name = "name",
                lastName = "lastName",
                email = "email",
                image = "image",
                isAdmin = false
            )
        )

        `when`(userService.getUserAll()).thenReturn(users)

        val responseEntity = userController.getAll()

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == users)
    }

    @Test
    fun `test get a particular user`() {
        val user = UserResponseDto(
            id = "123",
            name = "name",
            lastName = "lastName",
            email = "email",
            image = "image",
            isAdmin = false
        )

        `when`(userService.getUserItem("123")).thenReturn(user)

        val responseEntity = userController.userItem("123")

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == user)
    }

    @Test
    fun `test update a particular user`() {
        val user = UserResponseDto(
            id = "123",
            name = "name",
            lastName = "lastName",
            email = "email",
            image = "image",
            isAdmin = false
        )

        `when`(userService.updateDetail("123", user)).thenReturn(user)

        val responseEntity = userController.updateDetail("123", user)

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