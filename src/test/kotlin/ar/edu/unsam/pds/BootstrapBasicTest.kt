package ar.edu.unsam.pds

import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import ar.edu.unsam.pds.security.models.Principal
import org.junit.jupiter.api.BeforeEach
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

open class BootstrapBasicTest {
    private val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder(10)

    var principals = mutableListOf<Principal>()
    var users = mutableListOf<User>()
    var schedules = mutableListOf<Schedule>()
    var assignments = mutableListOf<Assignment>()
    var courses = mutableListOf<Course>()
    var institutions = mutableListOf<Institution>()

    private fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }

    @BeforeEach
    fun setUp() {
        users.add(
            User(
                name = "Adam",
                lastName = "AdamAdam",
                email = "adam@email.com",
                image = "",
                credits = 100000.0,
                isAdmin = true
            )
        )

        users.add(
            User(
                name = "Eve",
                lastName = "Eve",
                email = "eve@email.com",
                image = "",
                credits = 100000.0
            )
        )

        users.add(
            User(
                name = "Boniface",
                lastName = "Gomez",
                email = "boniface@email.com",
                image = "",
            )
        )

        users.forEachIndexed { i, it ->
            principals.add(Principal().apply {
                username = it.email
                password = encode(i.toString())
                user = it
                this.initProperties()
            })
        }

        schedules.add(
            Schedule(
                days = listOf(DayOfWeek.MONDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(20, 0),
                startDate = LocalDate.of(2023, 3, 1),
                endDate = LocalDate.of(2024, 10, 30),
                recurrenceWeeks = RecurrenceWeeks.WEEKLY,
            )
        )

        schedules.add(
            Schedule(
                days = listOf(DayOfWeek.TUESDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(21, 0),
                startDate = LocalDate.of(2023, 3, 1),
                endDate = LocalDate.of(2024, 12, 30),
                recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
            )
        )

        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[0]
            )
        )

        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = schedules[1]
            )
        )

        courses.add(Course(
            title = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(this@BootstrapBasicTest.assignments[0])
        })

        courses.add(Course(
            title = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        ).apply {
            addAssignment(this@BootstrapBasicTest.assignments[1])
        })

        courses.add(
            Course(
                title = "yoga",
                description = "yoga course",
                category = "yoga_category",
                image = ""
            )
        )

        institutions.add(Institution(
            name = "Enchanted Dance",
            description = "dance institution",
            category = "dance_category",
            image = ""
        ).apply {
            addCourse(this@BootstrapBasicTest.courses[0])
            addCourse(this@BootstrapBasicTest.courses[1])
            addAdmin(principals[0].user!!)
        })

        institutions.add(Institution(
            name = "The Kingdom of Calculations",
            description = "mathematics institution",
            category = "mathematics_category",
            image = "",
        ))

        institutions.add(
            Institution(
                name = "Serenity and Postures",
                description = "yoga institution",
                category = "yoga_category",
                image = ""
            )
        )
    }
}