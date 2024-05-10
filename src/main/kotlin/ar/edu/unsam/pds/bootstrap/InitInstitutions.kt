package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    @Autowired private lateinit var institutionRepository: InstitutionRepository

    override fun doAfterPropertiesSet() {
        institutionRepository.create(
            Institution(
                "Sport Club",
                "cadena de gimnasios",
                "Funcional",
                "https://www.sportclub.com.ar/assets/logo-nav-bad008ff.png"
            )
        )

        institutionRepository.create(
            Institution(
                "Punto Fit",
                "Club de Yoga",
                "Yoga",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIU23JHmnjOPv7N3csEVpxeseaTLOEHJg300Y4DJQvOw&s"
            )
        )

        institutionRepository.create(
            Institution(
                "Club Tres de Febrero",
                "Club de Natacion",
                "Natacion",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSu98a-RFTDCxeiT2I3QYJz4ZPIPTqdr6e9-mkumkCiFw&s"
            )
        )
    }
}
