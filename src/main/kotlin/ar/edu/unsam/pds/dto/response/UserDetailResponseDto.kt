package ar.edu.unsam.pds.dto.response

data class UserDetailResponseDto(
    val name: String,
    val lastName: String,
    val email: String,
    val image: String,
    val id: String,
    var isAdmin: Boolean,
    var nextClass: SubscriptionResponseDto?,
    var credits: Double?
)