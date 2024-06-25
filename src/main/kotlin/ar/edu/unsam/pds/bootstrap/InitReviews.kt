package ar.edu.unsam.pds.bootstrap

import ar.edu.unsam.pds.models.*
import ar.edu.unsam.pds.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.DependsOn
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component(value = "InitReviews.beanName")
@DependsOn(value = ["InitUsers.beanName", "InitAssignments.beanName"])
class InitReviews : BootstrapGeneric("Subscriptions and reviews") {
    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var courseRepository: CourseRepository
    @Autowired private lateinit var assignmentRepository: AssignmentRepository
    @Autowired private lateinit var paymentRepository: PaymentRepository
    @Autowired private lateinit var reviewRepository: ReviewRepository

    private lateinit var assignments: List<Assignment>

    private lateinit var userTemp: User
    private lateinit var courseTemp: Course

    override fun doAfterPropertiesSet() {
        assignments = assignmentRepository.findAll()

        // #############################################################################################################
        userTemp = this.findUser("Zeferina")
        courseTemp = this.findCourseByAssignment(assignments[0])

        userTemp.subscribe(assignments[0])
        assignments[0].addSubscribedUser(userTemp)

        userRepository.save(userTemp)

        paymentRepository.save(Payment(
            amount = assignments[0].price,
            date = LocalDateTime.now(),
            status = "APPROVED",
            paymentMethod = "CREDITS",
            user = userTemp,
            assignment = assignments[0]
        ))

        reviewRepository.save(Review(
            user = userTemp,
            course = courseTemp,
            rating = 3,
            description = "desciption of Zeferina"
        ))

        // #############################################################################################################
        userTemp = this.findUser("Valentina")
        courseTemp = this.findCourseByAssignment(assignments[0])

        userTemp.subscribe(assignments[0])
        assignments[0].addSubscribedUser(userTemp)

        userRepository.save(userTemp)

        paymentRepository.save(Payment(
            amount = assignments[0].price,
            date = LocalDateTime.now(),
            status = "APPROVED",
            paymentMethod = "CREDITS",
            user = userTemp,
            assignment = assignments[0]
        ))

        reviewRepository.save(Review(
            user = userTemp,
            course = courseTemp,
            rating = 4,
            description = "desciption of Valentina"
        ))

        // #############################################################################################################
        userTemp = this.findUser("Ursula")
        courseTemp = this.findCourseByAssignment(assignments[0])

        userTemp.subscribe(assignments[0])
        assignments[0].addSubscribedUser(userTemp)

        userRepository.save(userTemp)

        paymentRepository.save(Payment(
            amount = assignments[0].price,
            date = LocalDateTime.now(),
            status = "APPROVED",
            paymentMethod = "CREDITS",
            user = userTemp,
            assignment = assignments[0]
        ))

        reviewRepository.save(Review(
            user = userTemp,
            course = courseTemp,
            rating = 5,
            description = "desciption of Ursula"
        ))
    }

    fun findCourseByAssignment(assignment: Assignment): Course {
        return courseRepository.findAll().firstOrNull {
            it.assignments.any { a -> a.id == assignment.id }
        }!!
    }

    fun findUser(name : String): User {
        return userRepository.findAll().firstOrNull {
            it.name == name
        }!!
    }
}