package ar.edu.unsam.pds.dto.response

data class AssignmentResponseDto(
    val id: String,
    val quotas: Int,
    var quantityAvailable: Int,
    var isActive: Boolean,
    val price: Double,
    val schedule: ScheduleResponseDto
)