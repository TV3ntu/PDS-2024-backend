package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.*
import ar.edu.unsam.pds.models.Course

object CourseMapper {

    fun buildCourseDto(course: Course): CourseResponseDto {
        return CourseResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image
        )
    }

    fun buildCourseDetailDto(course: Course): CourseDetailResponseDto {
        val assignments = if(course.assignments.isEmpty()) mutableSetOf<AssignmentResponseDto>() else course.assignments.map { AssignmentMapper.buildAssignmentDto(it) }.toMutableSet()

        return CourseDetailResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image,
            assignments = assignments
        )
    }

    fun buildCourseStatsDto(course: Course): CourseStatsResponseDto {

        val totalAssignments = if (course.assignments.isEmpty()) 0 else course.assignments.size
        val totalSubscriptions = if (course.assignments.isEmpty()) 0 else course.totalSubscribedUsers()
        val totalIncome = if (course.assignments.isEmpty()) 0.0 else course.totalIncome()
        val mostPopularAssignment = if (course.assignments.isEmpty()) null else AssignmentMapper.buildAssignmentStatsDto(course.mostPopularAssignment())
        val mostProfitableAssignment = if (course.assignments.isEmpty()) null else AssignmentMapper.buildAssignmentStatsDto(course.mostProfitableAssignment())
        val assignments = if (course.assignments.isEmpty()) mutableSetOf() else course.assignments.map { AssignmentMapper.buildAssignmentStatsDto(it) }.toMutableSet()

        return CourseStatsResponseDto(
            id = course.id.toString(),
            title = course.title,
            description = course.description,
            category = course.category,
            image = course.image,
            totalAssignments = totalAssignments,
            totalSubscriptions = totalSubscriptions,
            totalIncome = totalIncome,
            mostPopularAssignment = mostPopularAssignment,
            mostProfitableAssignment = mostProfitableAssignment,
            assignments = assignments
        )
    }
}