package ar.edu.unsam.pds.dto.response

class CourseStatsResponseDto (
    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val image: String,
    val totalAssignments: Int,
    val totalSubscriptions: Int,
    val totalIncome: Double,
    val mostPopularAssignment: String,
    val mostProfitableAssignment: String,
    val assignment


) {}
