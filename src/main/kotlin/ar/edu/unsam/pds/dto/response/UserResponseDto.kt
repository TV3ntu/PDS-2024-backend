package ar.edu.unsam.pds.dto.response

data class UserResponseDto(
    val id: String,
    val name: String,
    val lastName: String,
    val email: String,
    val image: String
)
