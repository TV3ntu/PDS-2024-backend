package ar.edu.unsam.pds.bootstrap

import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    override fun doAfterPropertiesSet() {
    }
}