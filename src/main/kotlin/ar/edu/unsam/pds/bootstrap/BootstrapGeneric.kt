package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.ProjectApplication
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean

abstract class BootstrapGeneric(private val message: String) : InitializingBean {
    private val log: Logger = LoggerFactory.getLogger(ProjectApplication::class.java)

    override fun afterPropertiesSet() {
        log.info("####################################################################################################")
        log.info(String.format("%-99s", "# Loading $message ...") + "#")
        log.info("####################################################################################################")

        this.doAfterPropertiesSet()
    }

    abstract fun doAfterPropertiesSet()

    fun clean(text: String): String {
        return text.replace("\n", "").replace("    ", "")
    }
}