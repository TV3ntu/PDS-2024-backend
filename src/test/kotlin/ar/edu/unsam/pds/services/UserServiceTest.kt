package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.RegisterFormDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.repository.*
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.utils.Mapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@DataJpaTest
class UserServiceTest {
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var principalRepository: PrincipalRepository
    @Autowired private lateinit var institutionRepository: InstitutionRepository

    @Autowired private lateinit var  scheduleRepository: ScheduleRepository
    @Autowired private lateinit var assignmentRepository: AssignmentRepository
    @Autowired private lateinit var courseRepository: CourseRepository

    private lateinit var institutionService: InstitutionService
    private lateinit var userService: UserService

    private var users = mutableListOf<User>()
    private var schedules = mutableListOf<Schedule>()
    private var assingnmets = mutableListOf<Assignment>()
    private var courses = mutableListOf<Course>()
    private var institutions = mutableListOf<Institution>()

    @BeforeEach
    fun setUp() {
        institutionService = InstitutionService(
            institutionRepository = institutionRepository
        )

        userService = UserService(
            userRepository = userRepository,
            principalRepository = principalRepository,
            institutionService = institutionService
        )

        users.add(User(
            name = "Adan",
            lastName = "AdanAdan",
            email = "adan@email.com",
            image = ""
        ))

        users.add(User(
            name = "Eva",
            lastName = "EvaEva",
            email = "eva@email.com",
            image = ""
        ))

        users.add(User(
            name = "Bonifacio",
            lastName = "Gomez",
            email = "bonifacio@email.com",
            image = "",
        ))

        userRepository.saveAll(users)

        schedules.add(Schedule(
            days = listOf(DayOfWeek.MONDAY),
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(20, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2024, 10, 30),
            recurrenceWeeks = RecurrenceWeeks.WEEKLY,
        ))

        schedules.add(Schedule(
            days = listOf(DayOfWeek.TUESDAY),
            startTime = LocalTime.of(19, 0),
            endTime = LocalTime.of(21, 0),
            startDate = LocalDate.of(2023, 3, 1),
            endDate = LocalDate.of(2024, 12, 30),
            recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
        ))

        scheduleRepository.saveAll(schedules)

        assingnmets.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[0]
        ))

        assingnmets.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[1]
        ))

        assignmentRepository.saveAll(assingnmets)

        courses.add(Course(
            title = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(assingnmets[0])
        })

        courses.add(Course(
            title = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(assingnmets[1])
        })

        courseRepository.saveAll(courses)

        institutions.add(Institution(
            name = "Enchanted Dance",
            description = "dance institution",
            category = "dance_category",
            image = ""
        ).apply {
            addCourse(this@UserServiceTest.courses[0])
            addCourse(this@UserServiceTest.courses[1])
        })

        institutionRepository.saveAll(institutions)
    }

    @Test
    fun `test register a user`() {
        val id = userService.register(RegisterFormDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            password = "123"
        )).id

        val obtainedValue = userService.getUserItem(id)
        val expectedValue = UserResponseDto(
            name = "juán",
            lastName = "perez",
            email = "juan_perez@email.com",
            image = "",
            id = id,
            isAdmin = false
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
        val obtainedValue = userService.getUserItem(users[0].id.toString())
        val expectedValue = Mapper.buildUserDto(users[0])

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test update a particular user`() {
        val obtainedValuePre = userService.getUserItem(users[0].id.toString())
        val expectedValuePre = Mapper.buildUserDto(users[0])

        assertEquals(obtainedValuePre, expectedValuePre)

        val adanUpdate = UserResponseDto(
            name = "Adan__",
            lastName = "AdanAdan__",
            email = "adan__@email.com",
            image = "__",
            id = users[0].id.toString(),
            isAdmin = false
        )

        userService.updateDetail(
            idUser = users[0].id.toString(),
            userDetail = adanUpdate
        )

        val obtainedValuePos = userService.getUserItem(users[0].id.toString())

        assertEquals(obtainedValuePos, adanUpdate)
    }

    @Test
    fun `test get subscribed courses`() {
        assertEquals(
            userService.getSubscribedCourses(users[0].id.toString()),
            mutableListOf<CourseResponseDto>()
        )

        users[0].addAssignment(assingnmets[0])
        assingnmets[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getSubscribedCourses(users[0].id.toString()),
            mutableListOf(Mapper.buildCourseDto(assingnmets[0].course))
        )
    }

    @Test
    fun `test get subscribed`() {
        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf<SubscriptionResponseDto>()
        )

        users[0].addAssignment(assingnmets[0])
        assingnmets[0].addSubscribedUser(users[0])
        userRepository.save(users[0])

        assertEquals(
            userService.getSubscriptions(users[0].id.toString()),
            mutableListOf(Mapper.buildSubscriptionDto(assingnmets[0], institutions[0]))
        )
    }
}