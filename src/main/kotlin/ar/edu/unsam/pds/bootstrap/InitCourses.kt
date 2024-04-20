package ar.edu.unsam.pds.bootstrap

import org.springframework.stereotype.Component
import org.springframework.context.annotation.DependsOn

@Component(value = "InitCourses.beanName")
@DependsOn(value = ["InitInstitutions.beanName"])
class InitCourses : BootstrapGeneric("Courses") {
    override fun doAfterPropertiesSet() {
    }
}