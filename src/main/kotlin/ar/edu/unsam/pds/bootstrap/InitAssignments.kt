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

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName", "InitSchedules.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    @Autowired private lateinit var scheduleRepository: ScheduleRepository
    @Autowired private lateinit var institutionRepository: InstitutionRepository
    @Autowired private lateinit var assignmentRepository: AssignmentRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // Estrellas en Movimiento + Ballet Clásico para Principiantes #################################################
        val course11 = this.findByNameAndCourseTitle(
            name = "Estrellas en Movimiento",
            title = "Ballet Clásico para Principiantes"
        )

        val assignment111 = Assignment(
            quotas = 100,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course11?.addAssignment(assignment111)
        assignmentRepository.save(assignment111)

        val assignment112 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course11?.addAssignment(assignment112)
        assignmentRepository.save(assignment112)

        val assignment113 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course11?.addAssignment(assignment113)
        assignmentRepository.save(assignment113)

        // Estrellas en Movimiento + Hip Hop Kids ######################################################################
        val course12 = this.findByNameAndCourseTitle(
            name = "Estrellas en Movimiento",
            title = "Hip Hop Kids"
        )

        val assignment121 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course12?.addAssignment(assignment121)
        assignmentRepository.save(assignment121)

        val assignment122 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course12?.addAssignment(assignment122)
        assignmentRepository.save(assignment122)

        val assignment123 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course12?.addAssignment(assignment123)
        assignmentRepository.save(assignment123)

        // Estrellas en Movimiento + Jazz Dance Junior #################################################################
        val course13 = this.findByNameAndCourseTitle(
            name = "Estrellas en Movimiento",
            title = "Jazz Dance Junior"
        )

        val assignment131 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course13?.addAssignment(assignment131)
        assignmentRepository.save(assignment131)

        val assignment132 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course13?.addAssignment(assignment132)
        assignmentRepository.save(assignment132)

        val assignment133 = Assignment(
            quotas = 10,
            isActive = true,
            price = 100.0,
            schedule = findRandomSchedule()
        )

        course13?.addAssignment(assignment133)
        assignmentRepository.save(assignment133)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // El Escenario Encantado + Actuación para Niños ###############################################################
        val course21 = this.findByNameAndCourseTitle(
            name = "El Escenario Encantado",
            title = "Actuación para Niños"
        )

        val assignment211 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course21?.addAssignment(assignment211)
        assignmentRepository.save(assignment211)

        val assignment212 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course21?.addAssignment(assignment212)
        assignmentRepository.save(assignment212)

        val assignment213 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course21?.addAssignment(assignment213)
        assignmentRepository.save(assignment213)

        // El Escenario Encantado + Taller de Magia e Ilusionismo ######################################################
        val course22 = this.findByNameAndCourseTitle(
            name = "El Escenario Encantado",
            title = "Taller de Magia e Ilusionismo"
        )

        val assignment221 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course22?.addAssignment(assignment221)
        assignmentRepository.save(assignment221)

        val assignment222 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course22?.addAssignment(assignment222)
        assignmentRepository.save(assignment222)

        val assignment223 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course22?.addAssignment(assignment223)
        assignmentRepository.save(assignment223)

        // El Escenario Encantado + Dramaturgia Creativa para Adolescentes #############################################
        val course23 = this.findByNameAndCourseTitle(
            name = "El Escenario Encantado",
            title = "Dramaturgia Creativa para Adolescentes"
        )

        val assignment231 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course23?.addAssignment(assignment231)
        assignmentRepository.save(assignment231)

        val assignment232 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course23?.addAssignment(assignment232)
        assignmentRepository.save(assignment232)

        val assignment233 = Assignment(
            quotas = 15,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course23?.addAssignment(assignment233)
        assignmentRepository.save(assignment233)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        // El Rincón Creativo + Pintura y Dibujo para Niños ############################################################
        val course31 = this.findByNameAndCourseTitle(
            name = "El Rincón Creativo",
            title = "Pintura y Dibujo para Niños"
        )

        val assignment311 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course31?.addAssignment(assignment311)
        assignmentRepository.save(assignment311)

        val assignment312 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course31?.addAssignment(assignment312)
        assignmentRepository.save(assignment312)

        val assignment313 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course31?.addAssignment(assignment313)
        assignmentRepository.save(assignment313)

        // El Rincón Creativo + Artesanía Creativa #####################################################################
        val course32 = this.findByNameAndCourseTitle(
            name = "El Rincón Creativo",
            title = "Artesanía Creativa"
        )

        val assignment321 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course32?.addAssignment(assignment321)
        assignmentRepository.save(assignment321)

        val assignment322 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course32?.addAssignment(assignment322)
        assignmentRepository.save(assignment322)

        val assignment323 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course32?.addAssignment(assignment323)
        assignmentRepository.save(assignment323)

        // El Rincón Creativo + Explorando el Arte Digital #############################################################
        val course33 = this.findByNameAndCourseTitle(
            name = "El Rincón Creativo",
            title = "Explorando el Arte Digital"
        )

        val assignment331 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course33?.addAssignment(assignment331)
        assignmentRepository.save(assignment331)

        val assignment332 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course33?.addAssignment(assignment332)
        assignmentRepository.save(assignment332)

        val assignment333 = Assignment(
            quotas = 5,
            isActive = true,
            price = 200.0,
            schedule = findRandomSchedule()
        )

        course33?.addAssignment(assignment333)
        assignmentRepository.save(assignment333)
    }

    fun findByNameAndCourseTitle(name: String, title: String): Course? {
        val course = institutionRepository.findAll().find { it.name.contains(name) }.let { institution ->
            institution?.courses.let { courses ->
                courses?.find { it.title.contains(title) }
            }
        } ?: error("error find to $name and $title")

        return course
    }

    fun findRandomSchedule(): Schedule {
        scheduleRepository.findAll().randomOrNull()?.let {
            return it
        } ?: error("error find random schedule, schedule repository is empty")
    }
}