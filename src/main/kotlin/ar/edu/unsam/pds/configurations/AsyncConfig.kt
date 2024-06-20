package ar.edu.unsam.pds.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync

@Configuration
@EnableAsync
class AsyncConfig {

    @Bean("taskExecutor")
    fun taskExecutor(): TaskExecutor {
        return SimpleAsyncTaskExecutor()
    }
}