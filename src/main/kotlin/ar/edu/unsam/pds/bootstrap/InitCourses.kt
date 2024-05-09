package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Course
import ar.edu.unsam.pds.repository.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.context.annotation.DependsOn

@Component(value = "InitCourses.beanName")
@DependsOn(value = ["InitInstitutions.beanName"])
class InitCourses() : BootstrapGeneric("Courses") {

    @Autowired
    lateinit var courseRepository: CourseRepository

    override fun doAfterPropertiesSet() {
        val course1 = Course("Funcional Para Adolescente", "clases adaptadas para jovenes entre 18 y 35 años", "Adolescente","https://img.freepik.com/fotos-premium/entrenamiento-funcional-pareja-hombre-deportivo-mujer-forma-hacer-ejercicio-balon-medicinal-gimnasio_175682-12138.jpg")
        val course2 = Course("Funcional Intenso", "clases adaptadas con una mayor complejidad", "Intensivo","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQbZ27j2SUJdZBnq528ZB_OQs2Re_bkCu0ZB_czvfSowA&s")
        val course3 = Course("Yoga Para Adultos", "clases adaptadas para personas mayores de 50 años", "","https://i.blogs.es/1b86f3/yoga-adultos/1366_521.jpeg")
        val course4 = Course("Natacion Libre", "disfruta de la pileta climatizada", "","https://www.sanisidro.gob.ar/sites/default/files/img/styles/galeria_800x550/public/luces_led_1_1.jpg?itok=VcAbPuFZ")

        courseRepository.apply {
            create(course1)
            create(course2)
            create(course3)
            create(course4)
        }
    }
}