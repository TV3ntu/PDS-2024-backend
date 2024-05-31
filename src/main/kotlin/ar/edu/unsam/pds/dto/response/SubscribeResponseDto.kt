package ar.edu.unsam.pds.dto.response

import java.time.LocalDate

data class SubscribeResponseDto(
    val idUser: String,
    val idAssignment: String,
    val message: String,
    val date: LocalDate
)