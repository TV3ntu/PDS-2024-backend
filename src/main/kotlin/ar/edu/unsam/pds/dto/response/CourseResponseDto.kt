package ar.edu.unsam.pds.dto.response

import ar.edu.unsam.pds.models.Assignment

data class CourseResponseDto(
    val id: String,
    val title: String,
    val description: String,
    var category: String,
    var image: String
)

data class CourseDetailResponseDto(
    val id: String,
    val title: String,
    val description: String,
    var category: String,
    var image: String,
    var assignments: List<Assignment>
)