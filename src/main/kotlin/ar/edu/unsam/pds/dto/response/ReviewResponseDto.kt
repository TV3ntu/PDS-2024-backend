package ar.edu.unsam.pds.dto.response

data class ReviewResponseDto(
    val userId: String,
    val courseId: String,
    val rating: Int,
    val description: String
)