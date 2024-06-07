package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.User
import ar.edu.unsam.pds.repository.AssignmentRepository
import ar.edu.unsam.pds.repository.UserRepository
import ar.edu.unsam.pds.security.models.Principal
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.services.AssignmentService
import ar.edu.unsam.pds.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component(value = "InitUsers.beanName")
class InitUser : BootstrapGeneric("users") {
    @Autowired private lateinit var passwordEncoder: PasswordEncoder
    @Autowired private lateinit var principalRepository: PrincipalRepository
    @Autowired private lateinit var assignmentRepository: AssignmentRepository
    @Autowired private lateinit var assignmentService: AssignmentService
    @Autowired private lateinit var userRepository: UserRepository

    override fun doAfterPropertiesSet() {
        // region user = Adan @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        principalRepository.save(
            Principal().apply {
                username = "adan@email.com"
                password = encode("125")
                user = User(
                    name = "Adan",
                    lastName = "AdanAdan",
                    email = "adan@email.com",
                    image = ""
                )
                user!!.isAdmin = true



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
                    image = ""
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
                )

                this.initProperties()
            }
        )
        // endregion

        var assignments = assignmentRepository.findAll()
        var user = userRepository.findAll().get(0)
        for ( i in 0..3) {
            val assignment = assignments.get(i)
            assignmentService.subscribe(user.id.toString(), assignment.id.toString())
        }

    }



    fun encode(clave: String): String {
        return passwordEncoder.encode(clave)
    }
}