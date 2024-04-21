package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.LocalTime
import kotlin.io.path.createTempDirectory

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    override fun doAfterPropertiesSet() {
        var assignment1 = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Monday", "Wednesday"), 10, true, 100)
        var assignment2 = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Tuesday", "Thursday"), 10, true, 100)
        var assignment3 = Assignment(LocalTime.now(), LocalTime.now(), mutableListOf("Friday"), 10, true, 100)

        AssignmentRepository.apply {
            create(assignment1)
            create(assignment2)
            create(assignment3)
        }
    }
}