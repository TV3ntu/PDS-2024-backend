package ar.edu.unsam.pds.dto.response

import ar.edu.unsam.pds.models.Course

data class InstitutionResponseDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val image: String,
    val courses: Set<Course>
)