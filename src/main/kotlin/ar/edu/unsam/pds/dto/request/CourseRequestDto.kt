package ar.edu.unsam.pds.dto.request

data class CourseRequestDto(
    val title: String,
    val description: String,
    var category: String,
    var image: String,
    var institutionId: String
)