package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    @Autowired private lateinit var institutions: InstitutionRepository
    @Autowired private lateinit var assignments: AssignmentRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Sport Club + Entrenamiento Funcional ########################################################################
        val course11 = this.findByNameAndCourseTitle("Sport Club", "Entrenamiento Funcional")

        val assignment111 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        course11?.addAssignment(assignment111)
        assignments.create(assignment111)

        val assignment112 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        course11?.addAssignment(assignment112)
        assignments.create(assignment112)

        val assignment113 = Assignment(
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        course11?.addAssignment(assignment113)
        assignments.create(assignment113)

        // Sport Club + Nutrición y Salud ##############################################################################
        val course12 = this.findByNameAndCourseTitle("Sport Club", "Nutrición y Salud")

        val assignment121 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(9, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        course12?.addAssignment(assignment121)
        assignments.create(assignment121)

        val assignment122 = Assignment(
            LocalTime.of(13, 0),
            LocalTime.of(16, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        course12?.addAssignment(assignment122)
        assignments.create(assignment122)

        val assignment123 = Assignment(
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        course12?.addAssignment(assignment123)
        assignments.create(assignment123)

        // Sport Club + Entrenamiento de Resistencia y Cardio ##########################################################
        val course13 = this.findByNameAndCourseTitle("Sport Club", "Entrenamiento de Resistencia y Cardio")

        val assignment131 = Assignment(
            LocalTime.of(9, 0),
            LocalTime.of(10, 0),
            mutableListOf("Lunes", "Martes", "Viernes"),
            10,
            true,
            100
        )

        course13?.addAssignment(assignment131)
        assignments.create(assignment131)

        val assignment132 = Assignment(
            LocalTime.of(16, 0),
            LocalTime.of(17, 0),
            mutableListOf("Lunes", "Martes", "Viernes"),
            10,
            true,
            100
        )

        course13?.addAssignment(assignment132)
        assignments.create(assignment132)

        val assignment133 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            mutableListOf("Lunes", "Martes", "Viernes"),
            10,
            true,
            100
        )

        course13?.addAssignment(assignment133)
        assignments.create(assignment133)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Punto Fit + Introducción al Yoga ############################################################################
        val course21 = this.findByNameAndCourseTitle("Punto Fit", "Introducción al Yoga")

        val assignment211 = Assignment(
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            mutableListOf("Lunes"),
            15,
            true,
            200
        )

        course21?.addAssignment(assignment211)
        assignments.create(assignment211)

        val course212 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course21?.addAssignment(course212)
        assignments.create(course212)

        val assignment213 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course21?.addAssignment(assignment213)
        assignments.create(assignment213)

        // Punto Fit + Introducción al Yoga ############################################################################
        val course22 = this.findByNameAndCourseTitle("Punto Fit", "Yoga Terapéutico")

        val assignmen221 = Assignment(
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            mutableListOf("Lunes"),
            15,
            true,
            200
        )

        course22?.addAssignment(assignmen221)
        assignments.create(assignmen221)

        val assignmen222 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course22?.addAssignment(assignmen222)
        assignments.create(assignmen222)

        val assignmen223 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course22?.addAssignment(assignmen223)
        assignments.create(assignmen223)

        // Punto Fit + Introducción al Yoga ############################################################################
        val course23 = this.findByNameAndCourseTitle("Punto Fit", "Yoga Terapéutico")

        val assignmen231 = Assignment(
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            mutableListOf("Lunes"),
            15,
            true,
            200
        )

        course23?.addAssignment(assignmen231)
        assignments.create(assignmen231)

        val assignmen232 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Martes"),
            15,
            true,
            200
        )

        course23?.addAssignment(assignmen232)
        assignments.create(assignmen232)

        val assignmen233 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Miercoles"),
            15,
            true,
            200
        )

        course23?.addAssignment(assignmen233)
        assignments.create(assignmen233)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Club Tres de Febrero + Natacion Libre #######################################################################
        val course31 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación para Principiantes")

        val assignmen311 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course31?.addAssignment(assignmen311)
        assignments.create(assignmen311)

        val assignmen312 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course31?.addAssignment(assignmen312)
        assignments.create(assignmen312)

        val assignmen313 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course31?.addAssignment(assignmen313)
        assignments.create(assignmen313)

        // Club Tres de Febrero + Natacion Libre #######################################################################
        val course32 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación Avanzada")

        val assignmen321 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course32?.addAssignment(assignmen321)
        assignments.create(assignmen321)

        val assignmen322 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course32?.addAssignment(assignmen322)
        assignments.create(assignmen322)

        val assignmen323 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course32?.addAssignment(assignmen323)
        assignments.create(assignmen323)

        // Club Tres de Febrero + Natación para Bebés y Niños Pequeños##################################################
        val course33 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación para Bebés y Niños Pequeños")

        val assignmen331 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course33?.addAssignment(assignmen331)
        assignments.create(assignmen331)

        val assignmen332 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course33?.addAssignment(assignmen332)
        assignments.create(assignmen332)

        val assignmen333 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course33?.addAssignment(assignmen333)
        assignments.create(assignmen333)
    }

    fun findByNameAndCourseTitle(name: String, title: String): Course? {
        val course = institutions.getAll().find { it.name == name }.let { institution ->
            institution?.getCourses().let { courses ->
                courses?.find { it.title == title }
            }
        }

        if (course == null) error("error find to $name and $title")

        return course
    }
}