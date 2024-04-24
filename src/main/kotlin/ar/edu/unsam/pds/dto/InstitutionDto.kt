package ar.edu.unsam.pds.dto

import ar.edu.unsam.pds.models.Course

class InstitutionDto(val id: String, val name: String, val category: String,
                     val image: String, val courses: Set<Course>) {}