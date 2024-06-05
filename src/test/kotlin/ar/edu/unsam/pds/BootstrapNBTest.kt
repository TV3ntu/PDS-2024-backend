package ar.edu.unsam.pds

import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.repository.*
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

@DataJpaTest
class BootstrapNBTest {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(10)

    @Autowired lateinit var principalRepository: PrincipalRepository
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var scheduleRepository: ScheduleRepository
    @Autowired lateinit var assignmentRepository: AssignmentRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var institutionRepository: InstitutionRepository

    var principals = mutableListOf<Principal>()
    var users = mutableListOf<User>()
    var schedules = mutableListOf<Schedule>()
    var assignments = mutableListOf<Assignment>()
    var courses = mutableListOf<Course>()
    var institutions = mutableListOf<Institution>()

    fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }

    @BeforeEach
    fun setUp() {
        users.add(User(
            name = "Adam",
            lastName = "AdamAdam",
            email = "adam@email.com",
            image = ""
        ))

        users.add(User(
            name = "Eve",
            lastName = "Eve",
            email = "eve@email.com",
            image = ""
        ))

        users.add(User(
            name = "Boniface",
            lastName = "Gomez",
            email = "boniface@email.com",
            image = "",
        ))

        userRepository.saveAll(users)

        users.forEach {
            principals.add(Principal().apply {
                username = it.email
                password = encode("123")
                user = it
                this.initProperties()
            })
        }

        principalRepository.saveAll(principals)

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

        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[0]
            ))

        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[1]
            ))

        assignmentRepository.saveAll(assignments)

        courses.add(Course(
            title = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(this@BootstrapNBTest.assignments[0])
        })

        courses.add(Course(
            title = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(this@BootstrapNBTest.assignments[1])
        })

        courseRepository.saveAll(courses)

        institutions.add(Institution(
            name = "Enchanted Dance",
            description = "dance institution",
            category = "dance_category",
            image = ""
        ).apply {
            addCourse(this@BootstrapNBTest.courses[0])
            addCourse(this@BootstrapNBTest.courses[1])
        })

        institutionRepository.saveAll(institutions)
    }
}