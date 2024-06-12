package ar.edu.unsam.pds.dto.response

data class SubscriptionResponseDto (
    val assignmentId: String,
    val institutionName: String,
    val courseId: String,
    val courseName: String,
    val date: String,
    val hour: String,
    val status: String
)