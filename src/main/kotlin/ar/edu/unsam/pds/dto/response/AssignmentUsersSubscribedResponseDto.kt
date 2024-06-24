package ar.edu.unsam.pds.dto.response

data class AssignmentUsersSubscribedResponseDto(
    val assignment: AssignmentResponseDto,
    val users: MutableList<UserSubscribedResponseDto>
)