package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.models.Institution
import ar.edu.unsam.pds.repository.CourseRepository
import ar.edu.unsam.pds.repository.InstitutionRepository
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component

@Component(value = "InitCourses.beanName")
@DependsOn(value = ["InitInstitutions.beanName"])
class InitCourses : BootstrapGeneric("Courses") {
    private val institutions = InstitutionRepository
    private val courses = CourseRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val sportClub = this.findByName("Sport Club")

        val course1 = Course(
            "Funcional Para Adolescente",
            "clases adaptadas para jovenes entre 18 y 35 años",
            "Adolescente",
            "https://img.freepik.com/fotos-premium/entrenamiento-funcional-pareja-hombre-deportivo-mujer-forma-hacer-ejercicio-balon-medicinal-gimnasio_175682-12138.jpg"
        )

        sportClub?.addCourse(course1)
        courses.create(course1)

        val course2 = Course(
            "Funcional Intenso",
            "clases adaptadas con una mayor complejidad",
            "Intensivo",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbZ27j2SUJdZBnq528ZB_OQs2Re_bkCu0ZB_czvfSowA&s"
        )

        sportClub?.addCourse(course2)
        courses.create(course2)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val puntoFit = this.findByName("Punto Fit")

        val course3 = Course(
            "Yoga Para Adultos",
            "clases adaptadas para personas mayores de 50 años",
            "",
            "https://i.blogs.es/1b86f3/yoga-adultos/1366_521.jpeg"
        )

        puntoFit?.addCourse(course3)
        courses.create(course3)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val clubTresDeFebrero = this.findByName("Club Tres de Febrero")

        val course4 = Course(
            "Natacion Libre",
            "disfruta de la pileta climatizada",
            "",
            "https://www.sanisidro.gob.ar/sites/default/files/img/styles/galeria_800x550/public/luces_led_1_1.jpg?itok=VcAbPuFZ"
        )

        clubTresDeFebrero?.addCourse(course4)
        courses.create(course4)
    }

    fun findByName(name: String): Institution? {
        return institutions.getAll().find { it.name == name }
    }
}