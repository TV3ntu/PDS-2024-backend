package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserServiceTest : BootstrapNBTest() {
    private lateinit var institutionService: InstitutionService
    private lateinit var userService: UserService

    @BeforeEach
    fun prepareTestData() {
        institutionService = InstitutionService(
            institutionRepository = institutionRepository
        )

        userService = UserService(
            userRepository = userRepository,
            principalRepository = principalRepository,
            institutionService = institutionService
        )
    }

    @Test
    fun `test register a user`() {
        val id = userService.register(RegisterFormDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            password = "123"
        )).id

        val obtainedValue = userService.getUserDetail(id)
        val expectedValue = UserDetailResponseDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            image = "",
            id = id,
            isAdmin = false,
            nextClass = null
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `get all user`() {
        val obtainedValue = userService.getUserAll().toList()
        val expectedValue = users.map {
            Mapper.buildUserDto(it)
        }

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get a particular user`() {
        val obtainedValue = userService.getUserDetail(users[0].id.toString())
        val expectedValue = Mapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test update a particular user`() {
        val obtainedValuePre = userService.getUserDetail(users[0].id.toString())
        val expectedValuePre = Mapper.buildUserDetailDto(users[0], null)

        assertEquals(obtainedValuePre, expectedValuePre)

        val adanUpdate = UserResponseDto(
            name = "Adan__",
            lastName = "AdanAdan__",
            email = "adan__@email.com",
            image = "__",
            id = users[0].id.toString(),
            isAdmin = true
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
            nextClass = null
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
            mutableListOf(Mapper.buildCourseDto(assignments[0].course))
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
            mutableListOf(Mapper.buildSubscriptionDto(assignments[0], institutions[0]))
        )
    }
}