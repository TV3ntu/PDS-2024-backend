package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component(value = "InitUsers.beanName")
class InitUser : BootstrapGeneric("users") {
    @Autowired private lateinit var passwordEncoder: PasswordEncoder
    @Autowired private lateinit var principalRepository: PrincipalRepository

    override fun doAfterPropertiesSet() {
        // ADMIN
        principalRepository.save(
            Principal().apply {
                username = "admin@admin.com"
                password = encode("123")
                user = User(
                    name = "Admin",
                    lastName = "Admin",
                    email = "admin@admin.com",
                    image = "",
                    isAdmin = true,
                    credits = 1000.0
                )
                this.initProperties()
            }
        )

        // region user = Adan @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "adan@email.com"
                password = encode("125")
                user = User(
                    name = "Adan",
                    lastName = "AdanAdan",
                    email = "adan@email.com",
                    image = "",
                    credits = 1000.0,
                    isAdmin = true
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Eva @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "eva@email.com"
                password = encode("124")
                user = User(
                    name = "Eva",
                    lastName = "EvaEva",
                    email = "eva@email.com",
                    image = "",
                    credits = 10000.0
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Bonifacio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "bonifacio@email.com"
                password = encode("123")
                user = User(
                    name = "Bonifacio",
                    lastName = "Gomez",
                    email = "bonifacio@email.com",
                    image = "",
                    credits = 1000.0
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Clemente @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "clemente@email.com"
                password = encode("123")
                user = User(
                    name = "Clemente",
                    lastName = "Lopez",
                    email = "clemente@email.com",
                    image = "",
                    credits = 10000.0
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Dalmacio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "dalmacio@email.com"
                password = encode("123")
                user = User(
                    name = "Dalmacio",
                    lastName = "Martinez",
                    email = "dalmacio@email.com",
                    image = "",
                    credits = 10000.0
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Emeterio @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "emeterio@email.com"
                password = encode("123")
                user = User(
                    name = "Emeterio",
                    lastName = "Garcia",
                    email = "emeterio@email.com",
                    image = "",
                    credits = 10000.0
                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Taciana @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "taciana@email.com"
                password = encode("123")
                user = User(
                    name = "Taciana",
                    lastName = "Moyano",
                    email = "taciana@email.com",
                    image = "",
                    credits = 10000.0

                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Ursula @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "ursula@email.com"
                password = encode("123")
                user = User(
                    name = "Ursula",
                    lastName = "Campos",
                    email = "ursula@email.com",
                    image = "",
                    credits = 10000.0

                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Valentina @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "valentina@email.com"
                password = encode("123")
                user = User(
                    name = "Valentina",
                    lastName = "Soto",
                    email = "valentina@email.com",
                    image = "",
                    credits = 10000.0

                )

                this.initProperties()
            }
        )
        // endregion

        // region user = Zeferina @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "zeferina@email.com"
                password = encode("123")
                user = User(
                    name = "Zeferina",
                    lastName = "Chávez",
                    email = "zeferina@email.com",
                    image = "",
                    credits = 10000.0

                )

                this.initProperties()
            }
        )
        // endregion
    }

    fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }
}