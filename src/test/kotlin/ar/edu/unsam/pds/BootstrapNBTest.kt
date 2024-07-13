package ar.edu.unsam.pds

import ar.edu.unsam.pds.repository.*
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.services.EmailService
import ar.edu.unsam.pds.services.StorageService
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mock.web.MockMultipartFile
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices
import org.springframework.session.FindByIndexNameSessionRepository
import org.springframework.session.Session

@DataJpaTest
class BootstrapNBTest : BootstrapBasicTest() {
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

    @MockBean lateinit var sessionRepository: FindByIndexNameSessionRepository<Session>

    lateinit var file: MockMultipartFile

    @BeforeEach
    fun setUpBootstrapNBTest() {
        userRepository.saveAll(users)
        principalRepository.saveAll(principals)
        scheduleRepository.saveAll(schedules)
        assignmentRepository.saveAll(assignments)
        courseRepository.saveAll(courses)
        institutionRepository.saveAll(institutions)

        file = MockMultipartFile(
            "filename",
            "filename.jpg",
            "image/jpeg",
            "some content".toByteArray()
        )
    }
}