package ar.edu.unsam.pds.dto.response

import java.time.LocalTime

data class AssignmentResponseDto(
    val id: String,
    val quotas: Int,
    var isActive: Boolean,
    val price: Double,
    val scheduleResponseDto: ScheduleResponseDto
)