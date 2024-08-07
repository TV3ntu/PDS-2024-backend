package ar.edu.unsam.pds.controllers

import ar.edu.unsam.pds.dto.request.ReviewRequestDto
import ar.edu.unsam.pds.dto.response.ReviewResponseDto
import ar.edu.unsam.pds.mappers.ReviewMapper
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Review
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.services.ReviewService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import java.util.UUID

@ExtendWith(MockitoExtension::class)
class ReviewControllerTest {
    @Mock
    private lateinit var reviewService: ReviewService
    private lateinit var reviewController: ReviewController

    private lateinit var user: User
    private lateinit var course: Course
    private lateinit var review: Review
    private lateinit var principal: Principal

    @BeforeEach
    fun setUp() {
        reviewController = ReviewController()
        reviewController.reviewService = reviewService

        user = User(
            name = "name",
            lastName = "lastName",
            email = "name@email.com",
            image = "",
        ).apply {
            id = UUID.randomUUID()
        }

        principal = Principal().apply {
            username = this@ReviewControllerTest.user.email
            password = "666"
            user = this@ReviewControllerTest.user
            this.initProperties()
        }

        course = Course(
            title = "title",
            description = "description",
            category = "category",
            image = ""
        ).apply {
            id = UUID.randomUUID()
        }

        review = Review(
            user = user,
            course = course,
            rating = 3,
            description = "description"
        ).apply {
            id = UUID.randomUUID()
        }
    }

    @Test
    fun `test get all user reviews`() {
        val reviews = listOf(ReviewMapper.buildReviewResponseDto(review))

        `when`(reviewService.getAllUserReviews(principal)).thenReturn(reviews)

        val responseEntity = reviewController.getAllUserReviews(principal)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == reviews)
    }

    @Test
    fun `test get reviews of all courses`() {
        val id = course.id.toString()
        val reviews = listOf(ReviewMapper.buildReviewResponseDto(review))

        `when`(reviewService.getReviewsOfAllCourses(id)).thenReturn(reviews)

        val responseEntity = reviewController.getReviewsOfAllCourses(id)

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == reviews)
    }

    @Test
    fun `test create reviews`() {
        val reviewRequest = ReviewRequestDto(
            rating = review.rating,
            description = review.description
        )

        val reviewResponse = ReviewResponseDto(
            userId = principal.getUser().id.toString(),
            courseId = course.id.toString(),
            rating = review.rating,
            description = review.description
        )

        `when`(reviewService.createReviews(
            principal = principal,
            courseId = course.id.toString(),
            review = reviewRequest
        )).thenReturn(reviewResponse)

        val responseEntity = reviewController.createReviews(
            principal = principal,
            courseId = course.id.toString(),
            review = reviewRequest
        )

        assert(responseEntity.statusCode == HttpStatus.OK)
        assert(responseEntity.body == reviewResponse)
    }
}

