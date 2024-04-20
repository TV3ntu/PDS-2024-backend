package ar.edu.unsam.pds.bootstrap

import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
class InitInstitutions : BootstrapGeneric("Institutions") {
    override fun doAfterPropertiesSet() {
    }
}