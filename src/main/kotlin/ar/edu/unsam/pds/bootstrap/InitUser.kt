package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.UserRepository
import org.springframework.stereotype.Component

@Component(value = "InitUsers.beanName")
class InitUser : BootstrapGeneric("users") {
    private var userRepository = UserRepository

    override fun doAfterPropertiesSet() {
        // region user = Adan @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
            User(
                "Adan",
                "AdanAdan",
                "adan@email.com",
                ""
            )
        )

        // region user = Eva @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        userRepository.create(
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
            User(
                "Zeferina",
                "Chávez",
                "zeferina@email.com",
                "",
            )
        )
        // endregion
    }
}