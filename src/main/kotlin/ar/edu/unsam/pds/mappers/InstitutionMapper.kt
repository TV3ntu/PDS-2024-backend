package ar.edu.unsam.pds.mappers

import ar.edu.unsam.pds.dto.response.InstitutionDetailResponseDto
import ar.edu.unsam.pds.dto.response.InstitutionResponseDto
import ar.edu.unsam.pds.models.Institution

object InstitutionMapper {

    fun buildInstitutionDto(institution: Institution): InstitutionResponseDto {
        return InstitutionResponseDto(
            id = institution.id.toString(),
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = institution.image
        )
    }

    fun buildInstitutionDetailDto(institution: Institution): InstitutionDetailResponseDto {
        return InstitutionDetailResponseDto(
            id = institution.id.toString(),
            name = institution.name,
            description = institution.description,
            category = institution.category,
            image = institution.image,
            courses = institution.courses.map { CourseMapper.buildCourseDto(it) }.toMutableSet()
        )
    }
}