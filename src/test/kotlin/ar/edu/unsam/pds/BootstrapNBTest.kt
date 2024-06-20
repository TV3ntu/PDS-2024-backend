package ar.edu.unsam.pds

import ar.edu.unsam.pds.repository.*
import ar.edu.unsam.pds.security.repository.PrincipalRepository
import ar.edu.unsam.pds.services.EmailService
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean

@DataJpaTest
class BootstrapNBTest : BootstrapBasicTest() {
    @Autowired lateinit var principalRepository: PrincipalRepository
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var scheduleRepository: ScheduleRepository
    @Autowired lateinit var assignmentRepository: AssignmentRepository
    @Autowired lateinit var courseRepository: CourseRepository
    @Autowired lateinit var institutionRepository: InstitutionRepository
    @Autowired lateinit var paymentRepository: PaymentRepository
    @Autowired @MockBean lateinit var emailService: EmailService

    @BeforeEach
    fun setUpBootstrapNBTest() {
        userRepository.saveAll(users)
        principalRepository.saveAll(principals)
        scheduleRepository.saveAll(schedules)
        assignmentRepository.saveAll(assignments)
        courseRepository.saveAll(courses)
        institutionRepository.saveAll(institutions)
    }
}