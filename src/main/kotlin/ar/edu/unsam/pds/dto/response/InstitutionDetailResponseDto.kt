package ar.edu.unsam.pds.dto.response

data class InstitutionDetailResponseDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val image: String,
    val courses: MutableSet<CourseResponseDto>
)