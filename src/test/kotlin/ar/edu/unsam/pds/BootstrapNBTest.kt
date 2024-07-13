package ar.edu.unsam.pds

import ar.edu.unsam.pds.repository.*
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.services.EmailService
import ar.edu.unsam.pds.services.StorageService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate

@ActiveProfiles("test")
@SpringBootTest(classes = [ProjectApplication::class])
class BootstrapNBTest : BootstrapBasicTest() {
    @Autowired private lateinit var transactionManager: PlatformTransactionManager

    @Autowired lateinit var principalRepository: PrincipalRepository
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var scheduleRepository: ScheduleRepository
    @Autowired lateinit var assignmentRepository: AssignmentRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var institutionRepository: InstitutionRepository
    @Autowired lateinit var paymentRepository: PaymentRepository

    @Mock lateinit var imageService: StorageService
    @Mock lateinit var emailService: EmailService
    @Mock lateinit var rememberMeServices: TokenBasedRememberMeServices

    lateinit var file: MockMultipartFile

    @BeforeEach
    fun setUpBootstrapNBTest() {
        TransactionTemplate(transactionManager).execute {
            users = userRepository.saveAll(users)
            principals = principalRepository.saveAll(principals)
            schedules = scheduleRepository.saveAll(schedules)
            assignments = assignmentRepository.saveAll(assignments)
            courses = courseRepository.saveAll(courses)
            institutions = institutionRepository.saveAll(institutions)

            "status"
        }

        file = MockMultipartFile(
            "filename",
            "filename.jpg",
            "image/jpeg",
            "some content".toByteArray()
        )
    }

    fun deepEquals(self: Any?, other: Any?): Boolean {
        if (self === other) return true

        if (other !is Any) return false
        if (self !is Any) return false

        if (self.javaClass != other.javaClass) return false

        val properties = this.javaClass.declaredFields

        for (property in properties) {
            property.isAccessible = true
            val value1 = property.get(this)
            val value2 = property.get(other)

            if (value1 != value2) return false
        }

        return true
    }
}