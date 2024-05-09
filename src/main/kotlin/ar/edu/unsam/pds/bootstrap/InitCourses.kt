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
    @Autowired private lateinit var institutions: InstitutionRepository
    @Autowired private lateinit var courses: CourseRepository

    override fun doAfterPropertiesSet() {
        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val sportClub = this.findByName("Sport Club")

        val course11 = Course(
            "Entrenamiento Funcional",
            """
                Este curso se enfoca en enseñar técnicas de entrenamiento que mejoren la funcionalidad del cuerpo en
                actividades cotidianas y deportivas. Incluye ejercicios de fuerza, equilibrio, flexibilidad y movilidad.
            """.trimIndent(),
            "Funcional",
            "https://img.freepik.com/foto-gratis/personas-que-trabajan-interior-junto-pesas_23-2149175410.jpg"
        )

        sportClub?.addCourse(course11)
        courses.create(course11)

        val course12 = Course(
            "Nutrición y Salud",
            """
                Un entrenador personal podría ofrecer un curso que aborde los principios básicos de la nutrición y cómo
                aplicarlos para mejorar el rendimiento físico y la salud en general. Este curso podría incluir
                planificación de comidas, educación sobre macronutrientes y micronutrientes, y pautas para un estilo de
                vida saludable.
            """.trimIndent(),
            "Salud",
            "https://img.freepik.com/foto-gratis/mujeres-vista-frontal-comida-deliciosa_23-2149894894.jpg"
        )

        sportClub?.addCourse(course12)
        courses.create(course12)

        val course13 = Course(
            "Entrenamiento de Resistencia y Cardio",
            """
                Este curso se centraría en el desarrollo de programas de entrenamiento que mejoren la resistencia
                cardiovascular y la fuerza muscular. Los estudiantes aprenderían sobre diferentes métodos de
                entrenamiento cardiovascular, como correr, andar en bicicleta o nadar, así como también sobre técnicas
                de entrenamiento de fuerza con pesas, bandas de resistencia y peso corporal.
            """.trimIndent(),
            "Cardio",
            "https://img.freepik.com/foto-gratis/atleta-femenina-haciendo-ejercicio-gimnasio-ems_155003-10284.jpg"
        )

        sportClub?.addCourse(course13)
        courses.create(course13)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val puntoFit = this.findByName("Punto Fit")

        val course21 = Course(
            "Introducción al Yoga",
            """
                Este curso sería ideal para principiantes y personas que deseen conocer los fundamentos del yoga.
                Cubriría las posturas básicas (asanas), técnicas de respiración (pranayama), relajación y meditación,
                así como una introducción a la filosofía yóguica.
            """.trimIndent(),
            "Yoga",
            "https://img.freepik.com/foto-gratis/grupo-personas-entrenando-clase-yoga-alivio-cuerpo-alma-mente_53876-121280.jpg"
        )

        puntoFit?.addCourse(course21)
        courses.create(course21)

        val course22 = Course(
            "Yoga Terapéutico",
            """
                Dirigido a aquellos que buscan aliviar dolores específicos, recuperarse de lesiones o mejorar su salud
                en general. En este curso, se enseñarían técnicas de yoga adaptadas para abordar diferentes condiciones
                físicas y emocionales, como dolor de espalda, estrés, ansiedad, entre otros.
            """.trimIndent(),
            "Yoga",
            "https://img.freepik.com/foto-gratis/mujeres-clase-yoga-cerca_23-2148924630.jpg"
        )

        puntoFit?.addCourse(course22)
        courses.create(course22)

        val course23 = Course(
            "Yoga Avanzado",
            """
                Destinado a practicantes con experiencia que deseen profundizar en su práctica y desarrollar
                habilidades más avanzadas. Se abordarían posturas más desafiantes, técnicas de respiración más
                avanzadas, así como la integración de filosofía yóguica en la práctica diaria.
            """.trimIndent(),
            "Yoga",
            "https://img.freepik.com/foto-gratis/vista-lateral-mujer-hacer-yoga-estera_23-2148732887.jpg"
        )

        puntoFit?.addCourse(course23)
        courses.create(course23)

        // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        val clubTresDeFebrero = this.findByName("Club Tres de Febrero")

        val course31 = Course(
            "Natación para Principiantes",
            """
                Este curso estaría diseñado para personas que no saben nadar o tienen poca experiencia en el agua. Se
                enfocaría en enseñar habilidades básicas de flotación, respiración y propulsión, así como técnicas de
                seguridad en el agua.
            """.trimIndent(),
            "Natación",
            "https://img.freepik.com/foto-gratis/hombre-siendo-rescatado-agua_53876-15127.jpg"
        )

        clubTresDeFebrero?.addCourse(course31)
        courses.create(course31)

        val course32 = Course(
            "Natación Avanzada",
            """
                Dirigido a nadadores con experiencia que deseen perfeccionar su técnica y mejorar su rendimiento en el
                agua. Se abordarían aspectos más avanzados de la técnica de nado en estilos como crol, espalda, braza
                y mariposa, así como entrenamiento de resistencia y velocidad.
            """.trimIndent(),
            "Natación",
            "https://img.freepik.com/foto-gratis/nadadores-masculinos-nadando-piscina_23-2148687629.jpg"
        )

        clubTresDeFebrero?.addCourse(course32)
        courses.create(course32)

        val course33 = Course(
            "Natación para Bebés y Niños Pequeños",
            """
                Este curso se centraría en enseñar habilidades acuáticas básicas a bebés y niños pequeños, así como en
                fomentar la confianza y la comodidad en el agua desde una edad temprana. Se utilizarían técnicas de
                juego y actividades lúdicas para hacer que el aprendizaje sea divertido y seguro.
            """.trimIndent(),
            "Natación",
            "https://img.freepik.com/foto-gratis/pequeno-bebe-lindo-instructor-nino-madre-hijo_1157-42274.jpg"
        )

        clubTresDeFebrero?.addCourse(course33)
        courses.create(course33)
    }

    fun findByName(name: String): Institution? {
        return institutions.getAll().find { it.name == name }
    }
}