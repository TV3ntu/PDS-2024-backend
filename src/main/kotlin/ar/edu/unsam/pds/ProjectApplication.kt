package ar.edu.unsam.pds

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@OpenAPIDefinition
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = ["ar.edu.unsam.pds"])
class ProjectApplication

fun main(args: Array<String>) {
    runApplication<ProjectApplication>(*args)
}
