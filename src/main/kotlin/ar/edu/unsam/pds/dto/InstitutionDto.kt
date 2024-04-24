package ar.edu.unsam.pds.dto

import ar.edu.unsam.pds.models.Course

class InstitutionDto(val name: String, val category: String,
                     val image: String, val courses: Set<Course>) {}