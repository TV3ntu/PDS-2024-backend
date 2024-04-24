package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    override fun doAfterPropertiesSet() {
        var institution1 = Institution("Institution1", "A", "a.jpg")
        var institution2 = Institution("Institution2", "B", "a.jpg")
        var institution3 = Institution("Institution1", "C", "a.jpg")

        InstitutionRepository.apply {
            create(institution1)
            create(institution2)
            create(institution3)
        }
    }
}