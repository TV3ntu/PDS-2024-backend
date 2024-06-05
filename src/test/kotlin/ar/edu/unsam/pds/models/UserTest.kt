package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows

class UserTest {
    private lateinit var user: User
    private lateinit var assignments: MutableList<Assignment>
    private lateinit var courses: MutableList<Course>

    @BeforeEach
    fun setUp() {
        assignments = mutableListOf()
        courses = mutableListOf()

        user = User(
            name = "Bonifacio",
            lastName = "Gomez",
            email = "bonifacio@email.com",
            image = "",
        ).apply {
            id = UUID.randomUUID()
        }

        courses.add(Course(
            title = "classic dance",
            description = "classical dance course",
            category = "dance",
            image = ""
        ))

        courses.add(Course(
            title = "modern dance",
            description = "modern dance course",
            category = "dance",
            image = ""
        ))


        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = Schedule(
                    days = listOf(DayOfWeek.MONDAY),
                    startTime = LocalTime.of(19, 0),
                    endTime = LocalTime.of(20, 0),
                    startDate = LocalDate.of(2023, 3, 1),
                    endDate = LocalDate.of(2024, 10, 30),
                    recurrenceWeeks = RecurrenceWeeks.WEEKLY,
                ).apply {
                    id = UUID.randomUUID()
                }
            ).apply {
                id = UUID.randomUUID()
                course = courses[0]
            }
        )

        assignments.add(
            Assignment(
                quotas = 100,
                isActive = true,
                price = 100.0,
                schedule = Schedule(
                    days = listOf(DayOfWeek.TUESDAY),
                    startTime = LocalTime.of(19, 0),
                    endTime = LocalTime.of(21, 0),
                    startDate = LocalDate.of(2023, 3, 1),
                    endDate = LocalDate.of(2024, 12, 30),
                    recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
                ).apply {
                    id = UUID.randomUUID()
                }
            ).apply {
                id = UUID.randomUUID()
                course = courses[1]
            }
        )

        user.addAssignment(assignments[0])
        user.addAssignment(assignments[1])
    }

    @Test
    fun `Try getting all assignments from a user`() {
        assertEquals(assignments.toSet(), user.assignmentsList)
    }

    @Test
    fun `Try adding assignment to the user`() {
        val assignment = Assignment(
            quotas = 100,
            isActive = true,
            price = 100.0,
            schedule = Schedule(
                days = listOf(DayOfWeek.TUESDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(21, 0),
                startDate = LocalDate.of(2023, 3, 1),
                endDate = LocalDate.of(2024, 12, 30),
                recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
            ).apply {
                id = UUID.randomUUID()
            }
        ).apply {
            id = UUID.randomUUID()
        }

        user.addAssignment(assignment)
        assignments.add(assignment)

        assertEquals(assignments.toSet(), user.assignmentsList)
    }

    @Test
    fun `Try adding a repeat assignment to the user`() {
        val assignment = Assignment(
            quotas = 100,
            isActive = true,
            price = 100.0,
            schedule = Schedule(
                days = listOf(DayOfWeek.TUESDAY),
                startTime = LocalTime.of(19, 0),
                endTime = LocalTime.of(21, 0),
                startDate = LocalDate.of(2023, 3, 1),
                endDate = LocalDate.of(2024, 12, 30),
                recurrenceWeeks = RecurrenceWeeks.BIWEEKLY,
            ).apply {
                id = UUID.randomUUID()
            }
        ).apply {
            id = UUID.randomUUID()
        }

        assertThrows<ValidationException> {
            user.addAssignment(assignment)
            user.addAssignment(assignment)
        }
    }

    @Test
    fun `Try deleting an assignment to the user`() {
        user.removeAssignment(assignments[0])

        assertEquals(mutableSetOf(assignments[1]), user.assignmentsList)
    }

    @Test
    fun `Try repeat deleting an assignment to the user`() {
        assertThrows<ValidationException> {
            user.removeAssignment(assignments[0])
            user.removeAssignment(assignments[0])
        }
    }

    @Test
    fun `Try getting all courses from a user`() {
        assertEquals(courses.toSet(), user.subscribedCourses())
    }
}