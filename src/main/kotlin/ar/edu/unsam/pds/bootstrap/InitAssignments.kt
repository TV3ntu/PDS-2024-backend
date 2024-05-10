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
    @Autowired private lateinit var institutionRepository: InstitutionRepository
    @Autowired private lateinit var assignmentRepository: AssignmentRepository

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
        assignmentRepository.create(assignment111)

        val assignment112 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        course11?.addAssignment(assignment112)
        assignmentRepository.create(assignment112)

        val assignment113 = Assignment(
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        course11?.addAssignment(assignment113)
        assignmentRepository.create(assignment113)

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
        assignmentRepository.create(assignment121)

        val assignment122 = Assignment(
            LocalTime.of(13, 0),
            LocalTime.of(16, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        course12?.addAssignment(assignment122)
        assignmentRepository.create(assignment122)

        val assignment123 = Assignment(
            LocalTime.of(17, 0),
            LocalTime.of(19, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        course12?.addAssignment(assignment123)
        assignmentRepository.create(assignment123)

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
        assignmentRepository.create(assignment131)

        val assignment132 = Assignment(
            LocalTime.of(16, 0),
            LocalTime.of(17, 0),
            mutableListOf("Lunes", "Martes", "Viernes"),
            10,
            true,
            100
        )

        course13?.addAssignment(assignment132)
        assignmentRepository.create(assignment132)

        val assignment133 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            mutableListOf("Lunes", "Martes", "Viernes"),
            10,
            true,
            100
        )

        course13?.addAssignment(assignment133)
        assignmentRepository.create(assignment133)

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
        assignmentRepository.create(assignment211)

        val course212 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course21?.addAssignment(course212)
        assignmentRepository.create(course212)

        val assignment213 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course21?.addAssignment(assignment213)
        assignmentRepository.create(assignment213)

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
        assignmentRepository.create(assignmen221)

        val assignmen222 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course22?.addAssignment(assignmen222)
        assignmentRepository.create(assignmen222)

        val assignmen223 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        course22?.addAssignment(assignmen223)
        assignmentRepository.create(assignmen223)

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
        assignmentRepository.create(assignmen231)

        val assignmen232 = Assignment(
            LocalTime.of(12, 0),
            LocalTime.of(14, 0),
            mutableListOf("Martes"),
            15,
            true,
            200
        )

        course23?.addAssignment(assignmen232)
        assignmentRepository.create(assignmen232)

        val assignmen233 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Miercoles"),
            15,
            true,
            200
        )

        course23?.addAssignment(assignmen233)
        assignmentRepository.create(assignmen233)

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
        assignmentRepository.create(assignmen311)

        val assignmen312 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course31?.addAssignment(assignmen312)
        assignmentRepository.create(assignmen312)

        val assignmen313 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course31?.addAssignment(assignmen313)
        assignmentRepository.create(assignmen313)

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
        assignmentRepository.create(assignmen321)

        val assignmen322 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course32?.addAssignment(assignmen322)
        assignmentRepository.create(assignmen322)

        val assignmen323 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course32?.addAssignment(assignmen323)
        assignmentRepository.create(assignmen323)

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
        assignmentRepository.create(assignmen331)

        val assignmen332 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course33?.addAssignment(assignmen332)
        assignmentRepository.create(assignmen332)

        val assignmen333 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        course33?.addAssignment(assignmen333)
        assignmentRepository.create(assignmen333)
    }

    fun findByNameAndCourseTitle(name: String, title: String): Course? {
        val course = institutionRepository.getAll().find { it.name == name }.let { institution ->
            institution?.getCourses().let { courses ->
                courses?.find { it.title == title }
            }
        }

        if (course == null) error("error find to $name and $title")

        return course
    }
}