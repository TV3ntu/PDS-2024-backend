package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Schedule
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.repository.ScheduleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName", "InitSchedules.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    @Autowired
    private lateinit var scheduleRepository: ScheduleRepository
    @Autowired
    private lateinit var institutionRepository: InstitutionRepository
    @Autowired
    private lateinit var assignmentRepository: AssignmentRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Sport Club + Entrenamiento Funcional ########################################################################
        val course11 = this.findByNameAndCourseTitle("Sport Club", "Entrenamiento Funcional")

        val assignment111 = Assignment(
            100,
            true,
            100.0,
            findRandomSchedule()
        )

        course11?.addAssignment(assignment111)
        assignmentRepository.create(assignment111)

        val assignment112 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course11?.addAssignment(assignment112)
        assignmentRepository.create(assignment112)

        val assignment113 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course11?.addAssignment(assignment113)
        assignmentRepository.create(assignment113)

        // Sport Club + Nutrición y Salud ##############################################################################
        val course12 = this.findByNameAndCourseTitle("Sport Club", "Nutrición y Salud")

        val assignment121 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course12?.addAssignment(assignment121)
        assignmentRepository.create(assignment121)

        val assignment122 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course12?.addAssignment(assignment122)
        assignmentRepository.create(assignment122)

        val assignment123 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course12?.addAssignment(assignment123)
        assignmentRepository.create(assignment123)

        // Sport Club + Entrenamiento de Resistencia y Cardio ##########################################################
        val course13 = this.findByNameAndCourseTitle("Sport Club", "Entrenamiento de Resistencia y Cardio")

        val assignment131 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course13?.addAssignment(assignment131)
        assignmentRepository.create(assignment131)

        val assignment132 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course13?.addAssignment(assignment132)
        assignmentRepository.create(assignment132)

        val assignment133 = Assignment(
            10,
            true,
            100.0,
            findRandomSchedule()
        )

        course13?.addAssignment(assignment133)
        assignmentRepository.create(assignment133)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Punto Fit + Introducción al Yoga ############################################################################
        val course21 = this.findByNameAndCourseTitle("Punto Fit", "Introducción al Yoga")

        val assignment211 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course21?.addAssignment(assignment211)
        assignmentRepository.create(assignment211)

        val assignment212 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course21?.addAssignment(assignment212)
        assignmentRepository.create(assignment212)

        val assignment213 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course21?.addAssignment(assignment213)
        assignmentRepository.create(assignment213)

        // Punto Fit + Introducción al Yoga ############################################################################
        val course22 = this.findByNameAndCourseTitle("Punto Fit", "Yoga Terapéutico")

        val assignment221 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course22?.addAssignment(assignment221)
        assignmentRepository.create(assignment221)

        val assignment222 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course22?.addAssignment(assignment222)
        assignmentRepository.create(assignment222)

        val assignment223 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course22?.addAssignment(assignment223)
        assignmentRepository.create(assignment223)

        // Punto Fit + Introducción al Yoga ############################################################################
        val course23 = this.findByNameAndCourseTitle("Punto Fit", "Yoga Terapéutico")

        val assignment231 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course23?.addAssignment(assignment231)
        assignmentRepository.create(assignment231)

        val assignment232 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course23?.addAssignment(assignment232)
        assignmentRepository.create(assignment232)

        val assignment233 = Assignment(
            15,
            true,
            200.0,
            findRandomSchedule()
        )

        course23?.addAssignment(assignment233)
        assignmentRepository.create(assignment233)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Club Tres de Febrero + Natacion Libre #######################################################################
        val course31 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación para Principiantes")

        val assignment311 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course31?.addAssignment(assignment311)
        assignmentRepository.create(assignment311)

        val assignment312 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course31?.addAssignment(assignment312)
        assignmentRepository.create(assignment312)

        val assignment313 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course31?.addAssignment(assignment313)
        assignmentRepository.create(assignment313)

        // Club Tres de Febrero + Natacion Libre #######################################################################
        val course32 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación Avanzada")

        val assignment321 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course32?.addAssignment(assignment321)
        assignmentRepository.create(assignment321)

        val assignment322 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course32?.addAssignment(assignment322)
        assignmentRepository.create(assignment322)

        val assignment323 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course32?.addAssignment(assignment323)
        assignmentRepository.create(assignment323)

        // Club Tres de Febrero + Natación para Bebés y Niños Pequeños##################################################
        val course33 = this.findByNameAndCourseTitle("Club Tres de Febrero", "Natación para Bebés y Niños Pequeños")

        val assignment331 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course33?.addAssignment(assignment331)
        assignmentRepository.create(assignment331)

        val assignment332 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course33?.addAssignment(assignment332)
        assignmentRepository.create(assignment332)

        val assignment333 = Assignment(
            5,
            true,
            200.0,
            findRandomSchedule()
        )

        course33?.addAssignment(assignment333)
        assignmentRepository.create(assignment333)
    }

    fun findByNameAndCourseTitle(name: String, title: String): Course? {
        val course = institutionRepository.getAll().find { it.name == name }.let { institution ->
            institution?.courses.let { courses ->
                courses?.find { it.title == title }
            }
        } ?: error("error find to $name and $title")

        return course
    }

    fun findRandomSchedule(): Schedule {
        scheduleRepository.findRandomItem()?.let {
            return it
        } ?: error("error find random schedule, schedule repository is empty")
    }
}