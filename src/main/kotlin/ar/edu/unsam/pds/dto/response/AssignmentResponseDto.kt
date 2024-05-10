package ar.edu.unsam.pds.dto.response

import java.time.LocalDate
import java.time.LocalTime

data class AssignmentResponseDto(
    val id: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    var date: LocalDate,
    val quotas: Int,
    var isActive: Boolean,
    val price: Int
)