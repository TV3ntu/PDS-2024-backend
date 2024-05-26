package ar.edu.unsam.pds.dto.response

data class InstitutionResponseDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val image: String,
)