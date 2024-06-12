package ar.edu.unsam.pds.dto.response

data class AssignmentStatsResponseDto (
    val id: String,
    val quotas: Int,
    var quantityAvailable: Int,
    var isActive: Boolean,
    val price: Double,
    val name: String,
    val totalIncome: Double,
    val status: String,
    val schedule: ScheduleResponseDto,
    val subscribers : MutableSet<UserResponseDto>
)
