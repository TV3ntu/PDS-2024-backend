package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions() : BootstrapGeneric("Institutions") {

    @Autowired
    lateinit var institutionRepository: InstitutionRepository

    override fun doAfterPropertiesSet() {
        val institution1 = Institution("Sport Club", "club de futbol", "Funcional", "https://www.sportclub.com.ar/assets/logo-nav-bad008ff.png")
        val institution2 = Institution("Punto Fit", "club de yoga", "Yoga", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIU23JHmnjOPv7N3csEVpxeseaTLOEHJg300Y4DJQvOw&s")
        val institution3 = Institution("Club Tres de Febrero", "club de futbol", "Natacion", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu98a-RFTDCxeiT2I3QYJz4ZPIPTqdr6e9-mkumkCiFw&s")

        institutionRepository.apply {
            create(institution1)
            create(institution2)
            create(institution3)
        }
    }
}