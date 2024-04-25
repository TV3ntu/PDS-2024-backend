package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    override fun doAfterPropertiesSet() {
        val institution1 = Institution("Institution1", "A", "a.jpg")
        val institution2 = Institution("Institution2", "B", "a.jpg")
        val institution3 = Institution("Institution1", "C", "a.jpg")

        InstitutionRepository.apply {
            create(institution1)
            create(institution2)
            create(institution3)
        }
    }
}