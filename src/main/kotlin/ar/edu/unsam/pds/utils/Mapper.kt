package ar.edu.unsam.pds.utils

import ar.edu.unsam.pds.dto.response.*
import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User

object Mapper {
    fun buildUserDto(user: User): UserResponseDto {
        return UserResponseDto(user.name, user.lastName, user.email, user.image)
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

    fun buildCourseDetailDto(course: Course): CourseDetailResponseDto {
        return CourseDetailResponseDto(
            course.id,
            course.title,
            course.description,
            course.category,
            course.image,
            course.getAssignments().toList()
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