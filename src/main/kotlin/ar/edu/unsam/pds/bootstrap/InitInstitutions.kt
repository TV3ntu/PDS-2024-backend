package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.exceptions.NotFoundException
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.InstitutionRepository
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

@Component(value = "InitInstitutions.beanName")
@DependsOn(value = ["InitUsers.beanName"])

class InitInstitutions : BootstrapGeneric("Institutions") {
    @Autowired private lateinit var institutionRepository: InstitutionRepository
    @Autowired private lateinit var userRepository: UserRepository
    private val urlBase = "https://rafaeljosecalderon.github.io/PDS-2024-images/img"

    override fun doAfterPropertiesSet() {
        institutionRepository.save(
            Institution(
                name = "Estrellas en Movimiento: Academia de Danza Mágica",
                description = """
                    ¡Bienvenidos a Estrellas en Movimiento! Aquí, los sueños bailan y la magia se fusiona con el ritmo.
                    Nuestra academia ofrece un ambiente vibrante y acogedor donde niños y adolescentes exploran su 
                    pasión por la danza. Con instructores dedicados y programas que inspiran creatividad, cada paso es 
                    un viaje hacia el brillo del escenario. Únete a nuestra familia y deja que tus movimientos brillen 
                    como estrellas en el firmamento de la danza.
                """.trimIndent(),
                category = "Danza",
                image = "$urlBase/estrella_en_movimiento.png",
            ).apply {
                addAdmin(userByEmail("admin@admin.com"))
            }
        )

        institutionRepository.save(
            Institution(
                name = "El Escenario Encantado: Academia de Arte Dramático",
                description = """
                    ¡Bienvenidos a El Escenario Encantado! Aquí, la magia del teatro cobra vida. Nuestra academia ofrece
                     un espacio donde los sueños escénicos se convierten en realidad. Con un enfoque en la creatividad, 
                     la expresión y el desarrollo de habilidades, nuestros estudiantes exploran el mundo del arte 
                     dramático con pasión y dedicación. Únete a nuestra comunidad teatral y déjate llevar por el poder 
                     de la actuación.
                """.trimIndent(),
                category = "Teatro",
                image = "$urlBase/escenario_encantado.png",
            ).apply {
                addAdmin(userByEmail("admin@admin.com"))
            }
        )

        institutionRepository.save(
            Institution(
                name = "El Rincón Creativo: Academia de Arte Visual para Jóvenes",
                description = """
                    Descubre tu creatividad en El Rincón Creativo. Nuestra academia es un oasis de expresión artística 
                    donde niños y adolescentes exploran el mundo del arte visual. Con enfoque en técnicas diversas y 
                    libertad creativa, nuestros estudiantes desarrollan su talento mientras dan vida a sus visiones. 
                    Únete a nuestra comunidad y sumérgete en un viaje de colores, formas y texturas donde cada 
                    pincelada cuenta una historia única.
                """.trimIndent(),
                category = "Artes plasticas",
                image = "$urlBase/el_rincon_creativo.png"
            )
        )
    }

    fun userByEmail(mail : String): User {
        return userRepository.findByEmail(mail).orElseThrow {
            NotFoundException("usuario no encontrado")
        }
    }

}
