package ar.edu.unsam.pds.models

import ar.edu.unsam.pds.BootstrapBasicTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class AssignmentTest : BootstrapBasicTest() {
    @BeforeEach
    fun setUpAssignmentTest() {
        users[0].id = UUID.randomUUID()
        users[1].id = UUID.randomUUID()
        users[2].id = UUID.randomUUID()
    }

    @Test
    fun `get name day of assignment`() {
        assertEquals(assignments[0].name(), "MONDAY")
    }

    @Test
    fun `get total income`() {
        assignments[0].addSubscribedUser(users[0])
        assignments[0].addSubscribedUser(users[1])
        assignments[0].addSubscribedUser(users[2])

        assertEquals(assignments[0].totalIncome(), 300.0)
    }

    @Test
    fun `get total subscribed users`() {
        assignments[0].addSubscribedUser(users[0])
        assignments[0].addSubscribedUser(users[1])
        assignments[0].addSubscribedUser(users[2])

        assertEquals(assignments[0].totalSubscribedUsers(), 3)
    }

    @Test
    fun `Try has any subscribed user - 1`() {
        assignments[0].addSubscribedUser(users[0])

        assertTrue(assignments[0].hasAnySubscribedUser())
    }

    @Test
    fun `Try has any subscribed user - 2`() {
        assertFalse(assignments[0].hasAnySubscribedUser())
    }

    @Test
    fun `Try deleting an user to the assignment`() {
        assignments[0].addSubscribedUser(users[0])
        assignments[0].addSubscribedUser(users[1])
        assignments[0].addSubscribedUser(users[2])

        assertEquals(users.toMutableSet(), assignments[0].subscribedUsers)

        assignments[0].removeSubscribedUser(users[0])

        assertEquals(mutableSetOf(users[1], users[2]), assignments[0].subscribedUsers)
    }

    @Test
    fun `Try add subscribed user`() {
        assertEquals(mutableSetOf<User>(), assignments[0].subscribedUsers)

        assignments[0].addSubscribedUser(users[0])
        assignments[0].addSubscribedUser(users[1])
        assignments[0].addSubscribedUser(users[2])

        assertEquals(users.toMutableSet(), assignments[0].subscribedUsers)
    }

    @Test
    fun `Try attach course`() {
        assertEquals(courses[0], assignments[0].course)
    }

    @Test
    fun `Try quantity available`() {
        assertEquals(assignments[0].quantityAvailable(), 100)

        assignments[0].addSubscribedUser(users[0])
        assignments[0].addSubscribedUser(users[1])
        assignments[0].addSubscribedUser(users[2])

        assertEquals(assignments[0].quantityAvailable(), 97)
    }

    @Test
    fun `Try status`() {
        assertEquals(assignments[0].status(), "CONFIRMED")
    }
}