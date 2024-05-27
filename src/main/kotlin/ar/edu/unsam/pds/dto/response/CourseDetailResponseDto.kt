package ar.edu.unsam.pds.dto.response

import ar.edu.unsam.pds.models.Assignment

data class CourseDetailResponseDto(
    val id: String,
    val title: String,
    val description: String,
    var category: String,
    var image: String,
    val assignments: MutableSet<AssignmentResponseDto>
)