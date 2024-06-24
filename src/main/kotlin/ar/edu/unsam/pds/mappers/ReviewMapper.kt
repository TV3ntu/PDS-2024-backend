package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.ReviewResponseDto
import ar.edu.unsam.pds.models.Review

object ReviewMapper {
    fun buildReviewResponseDto(review: Review): ReviewResponseDto {
        return ReviewResponseDto(
            userId = review.user.id.toString(),
            courseId = review.course.id.toString(),
            rating = review.rating,
            description = review.description
        )
    }
}