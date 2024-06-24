package ar.edu.unsam.pds.services

import ar.edu.unsam.pds.dto.request.ReviewRequestDto
import ar.edu.unsam.pds.dto.response.ReviewResponseDto
import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.mappers.ReviewMapper
import ar.edu.unsam.pds.models.Review
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.ReviewRepository
import ar.edu.unsam.pds.security.models.Principal
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ReviewService(
    val reviewRepository: ReviewRepository,
    val courseRepository: CourseRepository
) {
    fun getAllUserReviews(principal: Principal): List<ReviewResponseDto> {
        val reviews = reviewRepository.getAllUserReviews(principal)
        return reviews.map { ReviewMapper.buildReviewResponseDto(it) }
    }

    fun getReviewsOfAllCourses(courseId: String): List<ReviewResponseDto> {
        val uuid = UUID.fromString(courseId)
        val reviews = reviewRepository.getReviewsOfAllCourses(uuid)
        return reviews.map { ReviewMapper.buildReviewResponseDto(it) }
    }

    fun createReviews(principal: Principal, courseId: String, review: ReviewRequestDto): ReviewResponseDto {
        val uuid = UUID.fromString(courseId)
        val course = courseRepository.findById(uuid).orElseThrow {
            NotFoundException("Curso inexistente con uuid provisto")
        }

        if (!courseRepository.isSubscribed(course, principal)) {
            throw NotFoundException("No se puede comentar a un curso al cual no se esta inscriptoo")
        }

        if (reviewRepository.existsReviewBy(course, principal)) {
            throw NotFoundException("No se puede comentar dos veces elmismo curso")
        }

        val newReview = Review(
            user = principal.getUser(),
            course = course,
            rating = review.rating,
            description = review.description
        )

        reviewRepository.save(newReview)

        return ReviewMapper.buildReviewResponseDto(newReview)
    }
}