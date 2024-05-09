package ar.edu.unsam.pds.utils

import ar.edu.unsam.pds.dto.response.AssignmentResponseDto
import ar.edu.unsam.pds.dto.response.CourseResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.dto.response.UserResponseDto
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User

object Mapper {
    fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(user.name, user.lastName, user.email, user.image, user.id)
    }

    fun buildInstitutionDto(institution: Institution): InstitutionResponseDto {
        return InstitutionResponseDto(institution.id, institution.name, institution.description, institution.category,
            institution.image)
    }

    fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(
            course.id,
            course.title,
            course.description,
            course.category,
            course.image
        )
    }

    fun buildAssignmentDto(assignment: Assignment): AssignmentResponseDto {
        return AssignmentResponseDto(
            assignment.id,
            assignment.startTime,
            assignment.endTime,
            assignment.day,
            assignment.quotas,
            assignment.isActive,
            assignment.price
        )
    }
}