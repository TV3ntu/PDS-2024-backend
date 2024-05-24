package ar.edu.unsam.pds.dto.response

import java.time.LocalTime

data class AssignmentResponseDto(
    val id: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    var day: MutableList<String>,
    val quotas: Int,
    var quantityAvailable: Int,
    var isActive: Boolean,
    val price: Int
)