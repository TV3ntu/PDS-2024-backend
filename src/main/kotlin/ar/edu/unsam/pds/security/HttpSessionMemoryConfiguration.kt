package ar.edu.unsam.pds.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.session.MapSessionRepository
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession

@EnableSpringHttpSession
@Configuration
class HttpSessionMemoryConfiguration {
    @Bean
    fun sessionRepository(): MapSessionRepository {
        return MapSessionRepository(HashMap())
    }
}