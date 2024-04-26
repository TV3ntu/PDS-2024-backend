package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    override fun doAfterPropertiesSet() {
        val institution1 = Institution("Sport Club", "Funcional", "https://www.sportclub.com.ar/assets/logo-nav-bad008ff.png")
        val institution2 = Institution("Punto Fit", "Yoga", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIU23JHmnjOPv7N3csEVpxeseaTLOEHJg300Y4DJQvOw&s")
        val institution3 = Institution("Club Tres de Febrero", "Natacion", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu98a-RFTDCxeiT2I3QYJz4ZPIPTqdr6e9-mkumkCiFw&s")

        InstitutionRepository.apply {
            create(institution1)
            create(institution2)
            create(institution3)
        }
    }
}