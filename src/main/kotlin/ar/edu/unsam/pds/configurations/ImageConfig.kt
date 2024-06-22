package ar.edu.unsam.pds.configurations

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.file.Path

@Configuration
class ImageConfig : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val dirName = "media"
        val uploadPath = Path.of(dirName).toFile().absolutePath

        registry
            .addResourceHandler("/$dirName/private/**")
            .addResourceLocations("file:$uploadPath/private/")

        registry
            .addResourceHandler("/$dirName/public/**")
            .addResourceLocations("file:$uploadPath/public/")
    }
}