package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

@Component(value = "InitCourses.beanName")
@DependsOn(value = ["InitInstitutions.beanName"])
class InitCourses : BootstrapGeneric("Courses") {
    @Autowired private lateinit var institutionRepository: InstitutionRepository
    @Autowired private lateinit var courseRepository: CourseRepository
    private val urlBase = "https://rafaeljosecalderon.github.io/PDS-2024-images/img"

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val estrellasEnMovimiento = this.findByName("Estrellas en Movimiento")

        val course11 = Course(
            title = "Ballet Clásico para Principiantes",
            description = """
                Un curso diseñado para niños y adolescentes que desean aprender los fundamentos del ballet clásico, 
                incluyendo postura, técnica de pies y brazos, y movimientos básicos como pliés, tendus y rond de jambes.
            """.trimIndent(),
            category = "Ballet",
            image = "$urlBase/estrellas_en_movimiento/ballet.jpg"
        )

        estrellasEnMovimiento?.addCourse(course11)
        courseRepository.save(course11)

        val course12 = Course(
            title = "Hip Hop Kids",
            description = """
                Una clase energética y divertida que introduce a los estudiantes a los fundamentos del hip hop, 
                incluyendo movimientos de popping, locking, y breakdance adaptados a su edad y habilidades.
            """.trimIndent(),
            category = "Hip Hop",
            image = "$urlBase//estrellas_en_movimiento/hip_hop.jpg"
        )

        estrellasEnMovimiento?.addCourse(course12)
        courseRepository.save(course12)

        val course13 = Course(
            title = "Jazz Dance Junior",
            description = """
                Un curso dinámico que combina elementos de ballet, danza contemporánea y estilos de baile moderno,
                enseñando a los estudiantes rutinas coreografiadas con énfasis en la expresión y el estilo personal.
            """.trimIndent(),
            category = "Jazz Dance",
            image = "$urlBase//estrellas_en_movimiento/jazz.jpg"
        )

        estrellasEnMovimiento?.addCourse(course13)
        courseRepository.save(course13)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val elEscenarioEncantado = this.findByName("El Escenario Encantado")

        val course21 = Course(
            title = "Actuación para Niños",
            description = """
                Un curso diseñado para niños de diversas edades que desean explorar el mundo del teatro. Incluye 
                ejercicios de improvisación, técnicas de expresión vocal y corporal, y la preparación de pequeñas obras 
                de teatro adaptadas a su edad.
            """.trimIndent(),
            category = "Actuación",
            image = "$urlBase//el_escenario_encantado/actuaci%C3%B3n_ni%C3%B1os.jpg"
        )

        elEscenarioEncantado?.addCourse(course21)
        courseRepository.save(course21)

        val course22 = Course(
            title = "Taller de Magia e Ilusionismo",
            description = """
                Una clase emocionante que enseña a los estudiantes los secretos detrás de los trucos de magia y 
                ilusionismo. Aprenderán a realizar trucos sorprendentes, a dominar la prestidigitación y a crear su 
                propia rutina mágica.
            """.trimIndent(),
            category = "Magia e Ilusionismo",
            image = "$urlBase//el_escenario_encantado/magia.jpg"
        )

        elEscenarioEncantado?.addCourse(course22)
        courseRepository.save(course22)

        val course23 = Course(
            title = "Dramaturgia Creativa para Adolescentes",
            description = """
                Un curso dirigido a adolescentes interesados en la escritura teatral. Aprenderán los conceptos básicos 
                de la dramaturgia, explorarán diferentes géneros teatrales y trabajarán en la creación de sus propios 
                guiones y escenas originales.
            """.trimIndent(),
            category = "Dramaturgia",
            image = "$urlBase//el_escenario_encantado/dramaturgia.jpg"
        )

        elEscenarioEncantado?.addCourse(course23)
        courseRepository.save(course23)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val elRinconCreativo = this.findByName("El Rincón Creativo")

        val course31 = Course(
            title = "Pintura y Dibujo para Niños",
            description = """
                Un curso diseñado para niños de todas las edades que deseen explorar su creatividad a través de la 
                pintura y el dibujo. Los estudiantes aprenderán técnicas básicas de pintura al óleo, acuarela, 
                acrílico y dibujo a lápiz, mientras desarrollan su estilo artístico único.
            """.trimIndent(),
            category = "Pintura y Dibujo",
            image = "$urlBase//el_rincon_creativo/pintura_ni%C3%B1os_.jpg"
        )

        elRinconCreativo?.addCourse(course31)
        courseRepository.save(course31)

        val course32 = Course(
            title = "Artesanía Creativa",
            description = """
                Una clase que introduce a los niños y adolescentes al mundo de la artesanía y las manualidades. Desde 
                la creación de joyas hasta la fabricación de esculturas con materiales reciclados, este curso fomenta 
                la imaginación y la destreza manual de los estudiantes.
            """.trimIndent(),
            category = "Artesanía",
            image = "$urlBase//el_rincon_creativo/artesania.jpg"
        )

        elRinconCreativo?.addCourse(course32)
        courseRepository.save(course32)

        val course33 = Course(
            title = "Explorando el Arte Digital",
            description = """
                Un taller que enseña a los estudiantes cómo utilizar herramientas digitales para crear arte. Desde la 
                ilustración digital hasta el diseño gráfico básico, este curso les proporciona las habilidades 
                necesarias para expresarse artísticamente en el mundo digital.
            """.trimIndent(),
            category = "Arte Digital",
            image = "$urlBase//el_rincon_creativo/arte_digital.jpg"
        )

        elRinconCreativo?.addCourse(course33)
        courseRepository.save(course33)
    }

    fun findByName(name: String): Institution? {
        return institutionRepository.findAll().find { it.name.contains(name) }
    }
}
