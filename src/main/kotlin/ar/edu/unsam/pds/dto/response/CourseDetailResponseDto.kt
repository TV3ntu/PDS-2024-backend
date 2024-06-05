package ar.edu.unsam.pds.dto.response

data class CourseDetailResponseDto(
    val id: String,
    val title: String,
    val description: String,
    var category: String,
    var image: String,
    val assignments: MutableSet<AssignmentResponseDto>
)