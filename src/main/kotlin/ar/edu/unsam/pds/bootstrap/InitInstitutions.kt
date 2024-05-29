package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    @Autowired private lateinit var institutionRepository: InstitutionRepository

    override fun doAfterPropertiesSet() {
        institutionRepository.save(
            Institution(
                name = "Sport Club",
                description = "cadena de gimnasios",
                category = "Funcional",
                image = "https://www.sportclub.com.ar/assets/logo-nav-bad008ff.png"
            )
        )

        institutionRepository.save(
            Institution(
                name = "Punto Fit",
                description = "Club de Yoga",
                category = "Yoga",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIU23JHmnjOPv7N3csEVpxeseaTLOEHJg300Y4DJQvOw&s"
            )
        )

        institutionRepository.save(
            Institution(
                name = "Club Tres de Febrero",
                description = "Club de Natacion",
                category = "Natacion",
                image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu98a-RFTDCxeiT2I3QYJz4ZPIPTqdr6e9-mkumkCiFw&s"
            )
        )
    }
}