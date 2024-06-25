package ar.edu.unsam.pds.dto.response

data class ReviewCourseResponseDto(
    var name: String,
    var lastName: String,
    val rating: Int,
    val description: String
)