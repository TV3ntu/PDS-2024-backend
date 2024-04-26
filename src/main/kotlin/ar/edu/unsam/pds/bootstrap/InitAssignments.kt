package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.Assignment
import ar.edu.unsam.pds.repository.AssignmentRepository
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.LocalTime

@Component(value = "InitAssignments.beanName")
@DependsOn(value = ["InitCourses.beanName"])
class InitAssignments : BootstrapGeneric("Assignments") {
    override fun doAfterPropertiesSet() {
        var funcionalAdolescente1 = Assignment( LocalTime.of(8, 0), LocalTime.of(10, 0), mutableListOf("Martes", "Jueves"), 10, true, 100)
        var funcionalAdolescente2 = Assignment(LocalTime.of(15, 0), LocalTime.of(17, 0), mutableListOf("Martes", "Jueves"), 10, true, 100)
        var funcionalAdolescente3 = Assignment(LocalTime.of(20, 0), LocalTime.of(22, 0), mutableListOf("Martes", "Jueves"), 10, true, 100)
        var funcionalIntenso1 = Assignment(LocalTime.of(9, 0), LocalTime.of(10, 0), mutableListOf("Lunes", "Miércoles", "Viernes"), 10, true, 100)
        var funcionalIntenso2 = Assignment(LocalTime.of(16, 0), LocalTime.of(17, 0), mutableListOf("Lunes", "Miércoles", "Viernes"), 10, true, 100)
        var yogaAdultos1 = Assignment(LocalTime.of(10, 0), LocalTime.of(11, 0), mutableListOf("Lunes"), 15, true, 200)
        var yogaAdultos2 = Assignment(LocalTime.of(18, 0), LocalTime.of(19, 0), mutableListOf("Jueves"), 15, true, 200)
        var natacionLibre1 = Assignment(LocalTime.of(8, 0), LocalTime.of(10, 0), mutableListOf("Sábado"), 5, true, 200)
        var natacionLibre2 = Assignment(LocalTime.of(11, 0), LocalTime.of(13, 0), mutableListOf("Sábado"), 5, true, 200)
        var natacionLibre3 = Assignment(LocalTime.of(15, 0), LocalTime.of(17, 0), mutableListOf("Sábado"), 5, true, 200)
        var natacionLibre4 = Assignment(LocalTime.of(18, 0), LocalTime.of(20, 0), mutableListOf("Sábado"), 5, true, 200)
        var natacionLibre5 = Assignment(LocalTime.of(21, 0), LocalTime.of(23, 0), mutableListOf("Sábado"), 5, true, 200)

        AssignmentRepository.apply {
            create(funcionalAdolescente1)
            create(funcionalAdolescente2)
            create(funcionalAdolescente3)
            create(funcionalIntenso1)
            create(funcionalIntenso2)
            create(yogaAdultos1)
            create(yogaAdultos2)
            create(natacionLibre1)
            create(natacionLibre2)
            create(natacionLibre3)
            create(natacionLibre4)
            create(natacionLibre5)
        }
    }
}