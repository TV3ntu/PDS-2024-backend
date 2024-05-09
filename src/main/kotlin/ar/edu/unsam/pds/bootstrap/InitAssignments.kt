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
        // #############################################################################################################
        val funcionalAdolescente = this.findByNameAndCourseTitle("Sport Club", "Funcional Para Adolescente")

        val funcionalAdolescente1 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        funcionalAdolescente?.addAssignment(funcionalAdolescente1)
        assignments.create(funcionalAdolescente1)

        val funcionalAdolescente2 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        funcionalAdolescente?.addAssignment(funcionalAdolescente2)
        assignments.create(funcionalAdolescente2)

        val funcionalAdolescente3 = Assignment(
            LocalTime.of(20, 0),
            LocalTime.of(22, 0),
            mutableListOf("Martes", "Jueves"),
            10,
            true,
            100
        )

        funcionalAdolescente?.addAssignment(funcionalAdolescente3)
        assignments.create(funcionalAdolescente3)

        // #############################################################################################################
        val funcionalIntenso = this.findByNameAndCourseTitle("Sport Club", "Funcional Intenso")

        val funcionalIntenso1 = Assignment(
            LocalTime.of(9, 0),
            LocalTime.of(10, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        funcionalIntenso?.addAssignment(funcionalIntenso1)
        assignments.create(funcionalIntenso1)

        val funcionalIntenso2 = Assignment(
            LocalTime.of(16, 0),
            LocalTime.of(17, 0),
            mutableListOf("Lunes", "Miércoles", "Viernes"),
            10,
            true,
            100
        )

        funcionalIntenso?.addAssignment(funcionalIntenso2)
        assignments.create(funcionalIntenso2)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // #############################################################################################################
        val yogaAdultos = this.findByNameAndCourseTitle("Punto Fit", "Yoga Para Adultos")

        val yogaAdultos1 = Assignment(
            LocalTime.of(10, 0),
            LocalTime.of(11, 0),
            mutableListOf("Lunes"),
            15,
            true,
            200
        )

        yogaAdultos?.addAssignment(yogaAdultos1)
        assignments.create(yogaAdultos1)

        val yogaAdultos2 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(19, 0),
            mutableListOf("Jueves"),
            15,
            true,
            200
        )

        yogaAdultos?.addAssignment(yogaAdultos2)
        assignments.create(yogaAdultos2)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // #############################################################################################################
        val natacionLibre = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natacion Libre")

        val natacionLibre1 = Assignment(
            LocalTime.of(8, 0),
            LocalTime.of(10, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        natacionLibre?.addAssignment(natacionLibre1)
        assignments.create(natacionLibre1)

        val natacionLibre2 = Assignment(
            LocalTime.of(11, 0),
            LocalTime.of(13, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        natacionLibre?.addAssignment(natacionLibre2)
        assignments.create(natacionLibre2)

        val natacionLibre3 = Assignment(
            LocalTime.of(15, 0),
            LocalTime.of(17, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        natacionLibre?.addAssignment(natacionLibre3)
        assignments.create(natacionLibre3)

        val natacionLibre4 = Assignment(
            LocalTime.of(18, 0),
            LocalTime.of(20, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        natacionLibre?.addAssignment(natacionLibre4)
        assignments.create(natacionLibre4)

        val natacionLibre5 = Assignment(
            LocalTime.of(21, 0),
            LocalTime.of(23, 0),
            mutableListOf("Sábado"),
            5,
            true,
            200
        )

        natacionLibre?.addAssignment(natacionLibre5)
        assignments.create(natacionLibre5)
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