package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.request.UserRequestDto
import ar.edu.unsam.pds.dto.response.SubscriptionResponseDto
import ar.edu.unsam.pds.dto.response.UserDetailResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.User

object UserMapper {
    fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image,
            id = user.id.toString(),
            isAdmin = user.isAdmin,
            credits = user.credits
        )
    }

    fun buildUserDetailDto(user: User, nextClass: SubscriptionResponseDto?): UserDetailResponseDto {
        return UserDetailResponseDto(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image,
            id = user.id.toString(),
            isAdmin = user.isAdmin,
            nextClass = nextClass,
            credits = user.credits
        )
    }

    fun patchUser(user: User, userDetail: UserRequestDto): User {
        user.name = userDetail.name
        user.lastName = userDetail.lastName
        user.email = userDetail.email
        user.image = userDetail.image
        user.credits = userDetail.credits ?: user.credits
        return user
    }
}