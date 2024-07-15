package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.BootstrapNBTest
import ar.edu.unsam.pds.dto.request.ReviewRequestDto
import ar.edu.unsam.pds.dto.response.ReviewResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.exceptions.ValidationException
import ar.edu.unsam.pds.models.Payment
import ar.edu.unsam.pds.models.Review
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.util.UUID

class ReviewServiceTest : BootstrapNBTest() {
    private lateinit var reviewService: ReviewService

    @BeforeEach
    fun prepareTestData() {
        reviewService = ReviewService(
            reviewRepository = reviewRepository,
            courseRepository = courseRepository
        )

        users[0].subscribe(assignments[0])
        assignments[0].addSubscribedUser(users[0])

        userRepository.save(users[0])
        assignmentRepository.save(assignments[0])

        paymentRepository.save(
            Payment(
                amount = assignments[0].price,
                date = LocalDateTime.now(),
                status = "APPROVED",
                paymentMethod = "CREDITS",
                user = users[0],
                assignment = assignments[0]
            )
        )

        reviewRepository.save(
            Review(
                user = users[0],
                course = courses[0],
                rating = 3,
                description = "description"
            )
        )

        users[1].subscribe(assignments[0])
        assignments[0].addSubscribedUser(users[1])

        userRepository.save(users[1])
        assignmentRepository.save(assignments[0])

        paymentRepository.save(
            Payment(
                amount = assignments[0].price,
                date = LocalDateTime.now(),
                status = "APPROVED",
                paymentMethod = "CREDITS",
                user = users[1],
                assignment = assignments[0]
            )
        )
    }

    @Test
    fun `test get all user reviews`() {
        val obtainedValue = reviewService.getAllUserReviews(principals[0])
        val expectedValue = listOf(
            ReviewResponseDto(
                userId = users[0].id.toString(),
                courseId = courses[0].id.toString(),
                rating = 3,
                description = "description"
            )
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test get reviews of all courses`() {
        val obtainedValue = reviewService.getReviewsOfAllCourses(courses[0].id.toString())
        val expectedValue = listOf(
            ReviewResponseDto(
                userId = users[0].id.toString(),
                courseId = courses[0].id.toString(),
                rating = 3,
                description = "description"
            )
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test create reviews`() {
        val review = ReviewRequestDto(
            rating = 3,
            description = "description description"
        )

        val obtainedValue = reviewService.createReviews(principals[1], courses[0].id.toString(), review)
        val expectedValue = ReviewResponseDto(
            userId = users[1].id.toString(),
            courseId = courses[0].id.toString(),
            rating = 3,
            description = "description description"
        )

        assertEquals(obtainedValue, expectedValue)
    }

    @Test
    fun `test create reviews - in exist course`() {
        val review = ReviewRequestDto(
            rating = 3,
            description = "description description"
        )

        assertThrows<NotFoundException> {
            reviewService.createReviews(principals[1], UUID.randomUUID().toString(), review)
        }
    }

    @Test
    fun `test create reviews - no subscription of course`() {
        val review = ReviewRequestDto(
            rating = 3,
            description = "description description"
        )

        assertThrows<NotFoundException> {
            reviewService.createReviews(principals[2], courses[0].id.toString(), review)
        }
    }

    @Test
    fun `test create reviews - with review`() {
        val review = ReviewRequestDto(
            rating = 3,
            description = "description description"
        )

        assertThrows<NotFoundException> {
            reviewService.createReviews(principals[0], courses[0].id.toString(), review)
        }
    }
}