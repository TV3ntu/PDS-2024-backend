package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component(value = "InitUsers.beanName")
class InitUser : BootstrapGeneric("users") {
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    private var userRepository = UserRepository

    override fun doAfterPropertiesSet() {
        // region user = Adan @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("125"),
            User(
                "Adan",
                "AdanAdan",
                "adan@email.com",
                ""
            )
        )
        // endregion

        // region user = Eva @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("124"),
            User(
                "Eva",
                "EvaEva",
                "eva@email.com",
                ""
            )
        )
        // endregion

        // region user = Bonifacio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Bonifacio",
                "Gomez",
                "bonifacio@email.com",
                "",
            )
        )
        // endregion

        // region user = Clemente @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Clemente",
                "Lopez",
                "clemente@email.com",
                "",
            )
        )
        // endregion

        // region user = Dalmacio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Dalmacio",
                "Martinez",
                "dalmacio@email.com",
                "",
            )
        )
        // endregion

        // region user = Emeterio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Emeterio",
                "Garcia",
                "emeterio@email.com",
                "",
            )
        )
        // endregion

        // region user = Taciana @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Taciana",
                "Moyano",
                "taciana@email.com",
                "",
            )
        )
        // endregion

        // region user = Ursula @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Ursula",
                "Campos",
                "ursula@email.com",
                "",
            )
        )
        // endregion

        // region user = Valentina @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Valentina",
                "Soto",
                "valentina@email.com",
                "",
            )
        )
        // endregion

        // region user = Zeferina @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            this.encode("123"),
            User(
                "Zeferina",
                "Chávez",
                "zeferina@email.com",
                "",
            )
        )
        // endregion
    }

    fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }
}