package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.LoginForm
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.request.UserRequestDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.exceptions.InternalServerError
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.AssignmentMapper
import ar.edu.unsam.pds.mappers.CourseMapper
import ar.edu.unsam.pds.mappers.UserMapper
import ar.edu.unsam.pds.security.models.Principal
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
class UserServiceTest : BootstrapNBTest() {
    @Mock
    private lateinit var mockRequest: HttpServletRequest

    private lateinit var institutionService: InstitutionService
    private lateinit var userService: UserService

    @BeforeEach
    fun prepareTestData() {
        institutionService = InstitutionService(
            institutionRepository = institutionRepository,
            principalRepository = principalRepository
        )

        userService = UserService(
            userRepository = userRepository,
            principalRepository = principalRepository,
            institutionService = institutionService,
            emailService = emailService
        )
    }

    @Test
    fun `test load user by username`() {
        val obtainedValue = userService.loadUserByUsername("adam@email.com")
        val expectedValue = principals[0]

        assertEquals(expectedValue, obtainedValue)
    }

    @Test
    fun `test throw load user by username`() {
        assertThrows<UsernameNotFoundException> {
            userService.loadUserByUsername("juan_perez@email.com")
        }
    }

    @Test
    fun `test login`() {
        val userForm = LoginForm("adam@email.com", "0")
        `when`(mockRequest.userPrincipal).thenReturn(object : Authentication {
            override fun getName() = principals[0].user?.name!!
            override fun getAuthorities() = principals[0].authorities
            override fun getCredentials(): Any? = null
            override fun getDetails(): Any? = null
            override fun getPrincipal() = principals[0]
            override fun isAuthenticated() = true
            override fun setAuthenticated(isAuthenticated: Boolean) {}
        })

        doNothing().`when`(mockRequest).login("adam@email.com", "0")

        val obtainedValue = userService.login(userForm, mockRequest)
        val expectedValue = UserDetailResponseDto(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = "",
            id = obtainedValue.id,
            isAdmin = true,
            nextClass = null,
            credits = 100000.0
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test login - not existed user`() {
        val userForm = LoginForm("adam@email.com", "0")

        `when`(mockRequest.login("adam@email.com","0")).thenThrow(ServletException())

        assertThrows<NotFoundException> {
            userService.login(userForm, mockRequest)
        }
    }

    @Test
    fun `test login - internal error`() {
        val userForm = LoginForm("adam@email.com", "0")
        val principal = Principal().apply {
            id = UUID.randomUUID()
            username = "juan perez"
            password = "666"
        }

        `when`(mockRequest.userPrincipal).thenReturn(object : Authentication {
            override fun getName() = "juan"
            override fun getAuthorities() = principal.authorities
            override fun getCredentials(): Any? = null
            override fun getDetails(): Any? = null
            override fun getPrincipal() = principal
            override fun isAuthenticated() = true
            override fun setAuthenticated(isAuthenticated: Boolean) {}
        })

        doNothing().`when`(mockRequest).login("adam@email.com", "0")

        assertThrows<InternalServerError> {
            userService.login(userForm, mockRequest)
        }
    }

    @Test
    fun `test register a user`() {
        val id = userService.register(
            RegisterFormDto(
                name = "juán",
                lastName = "perez",
                email = "juan_perez@email.com",
                password = "123"
            )
        ).id

        val obtainedValue = userService.getUserDetail(id)
        val expectedValue = UserDetailResponseDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            image = "",
            id = id,
            isAdmin = false,
            nextClass = null,
            credits = 0.0
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw register a user`() {
        assertThrows<InternalServerError>{
            userService.register(
                RegisterFormDto("juán", "perez", "adam@email.com", "123")
            )
        }
    }

    @Test
    fun `get all user`() {
        val obtainedValue = userService.getUserAll().toList()
        val expectedValue = users.map {
            UserMapper.buildUserDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular user`() {
        val obtainedValue = userService.getUserDetail(users[0].id.toString())
        val expectedValue = UserMapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test throw get a particular user`() {
        assertThrows<NotFoundException> {
            userService.getUserDetail("029ce681-9f90-45e7-af7f-e74a8cfb4b57")
        }
    }

    @Test
    fun `test update a particular user`() {
        val obtainedValuePre = userService.getUserDetail(users[0].id.toString())
        val expectedValuePre = UserMapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValuePre, expectedValuePre)

        val adanUpdate = UserRequestDto(
            name = "Adan__",
            lastName = "AdanAdan__",
            email = "adan__@email.com",
            image = "__",
            id = users[0].id.toString(),
            isAdmin = true,
            credits = 100000.0,
            nextClass = null
        )

        userService.updateDetail(
            idUser = users[0].id.toString(),
            userDetail = adanUpdate
        )

        val obtainedValuePos = userService.getUserDetail(users[0].id.toString())
        val expectedValuePos = UserDetailResponseDto(
            name = adanUpdate.name,
            lastName = adanUpdate.lastName,
            email = adanUpdate.email,
            image = adanUpdate.image,
            id = adanUpdate.id,
            isAdmin = adanUpdate.isAdmin,
            nextClass = null,
            credits = adanUpdate.credits
        )

        assertEquals(obtainedValuePos, expectedValuePos)
    }

    @Test
    fun `test get subscribed courses`() {
        assertEquals(
            userService.getSubscribedCourses(users[0].id.toString()),
            mutableListOf<CourseResponseDto>()
        )

        users[0].addAssignment(assignments[0])
        assignments[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getSubscribedCourses(users[0].id.toString()),
            mutableListOf(CourseMapper.buildCourseDto(assignments[0].course))
        )
    }

    @Test
    fun `test get subscribed`() {
        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf<SubscriptionResponseDto>()
        )

        users[0].addAssignment(assignments[0])
        assignments[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf(AssignmentMapper.buildSubscriptionDto(assignments[0], institutions[0]))
        )
    }
}