package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.BootstrapBasicTest
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.models.enums.RecurrenceWeeks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class UserTest : BootstrapBasicTest() {
    private lateinit var assignment: Assignment

    @BeforeEach
    fun setUpUserTest() {
        assignments[0].id = UUID.randomUUID()
        assignments[1].id = UUID.randomUUID()

        assignment = Assignment(
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
    }

    @Test
    fun `Try getting all assignments from a user`() {
        users[0].addAssignment(assignments[0])
        users[0].addAssignment(assignments[1])

        assertEquals(assignments.toSet(), users[0].assignmentsList)
    }

    @Test
    fun `Try adding assignment to the user`() {
        users[0].addAssignment(assignment)
        assertEquals(setOf(assignment), users[0].assignmentsList)
    }

    @Test
    fun `Try adding a repeat assignment to the user`() {
        assertThrows<ValidationException> {
            users[0].addAssignment(assignment)
            users[0].addAssignment(assignment)
        }
    }

    @Test fun `Try subscribing to an assignment`() {
        users[0].subscribe(assignment)
        assertEquals(setOf(assignment), users[0].assignmentsList)
    }

    @Test fun `Try subscribing with not enough credits`() {
        users[0].credits = 0.0
        assertThrows<ValidationException> {
            users[0].subscribe(assignment)
        }
    }

    @Test
    fun `Try deleting an assignment to the user`() {
        users[0].addAssignment(assignments[0])
        users[0].addAssignment(assignments[1])

        assertEquals(assignments.toMutableSet(), users[0].assignmentsList)

        users[0].removeAssignment(assignments[0])

        assertEquals(mutableSetOf(assignments[1]), users[0].assignmentsList)
    }

    @Test
    fun `Try repeat deleting an assignment to the user`() {
        users[0].addAssignment(assignments[0])
        users[0].addAssignment(assignments[1])

        assertThrows<ValidationException> {
            users[0].removeAssignment(assignments[0])
            users[0].removeAssignment(assignments[0])
        }
    }

    @Test
    fun `Try getting all courses from a user`() {
        users[0].addAssignment(assignments[0])
        users[0].addAssignment(assignments[1])

        assertEquals(courses.subList(0,2).toSet(), users[0].subscribedCourses())
    }
}