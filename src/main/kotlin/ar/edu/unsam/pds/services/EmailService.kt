package ar.edu.unsam.pds.services

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Profile
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths

@Service
@ConditionalOnProperty(name = ["email.enabled"], havingValue = "true", matchIfMissing = true)
class EmailService(
    private val mailSender: JavaMailSender
) {

    @Async("taskExecutor")
    fun sendEmail(to: String, subject: String, htmlContent: String) {
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        helper.setTo(to)
        helper.setSubject(subject)
        helper.setText(htmlContent, true)

        mailSender.send(message)
    }

    private fun loadTemplate(filePath: String): String {
        val path = Paths.get(filePath)
        return String(Files.readAllBytes(path))
    }

    private fun replacePlaceholders(template: String, placeholders: Map<String, String>): String {
        var result = template
        for ((key, value) in placeholders) {
            result = result.replace(key, value)
        }
        return result
    }

    @Async("taskExecutor")
    fun sendPaymentConfirmationEmail(to: String, amount: Double, userName : String, transactionId: String) {
        val subject = "Payment Confirmation"
        val template = loadTemplate("src/main/resources/templates/payment_complete.html")
        val placeholders = mapOf(
            "\${amount}" to amount.toString(),
            "\${user}" to userName,
            "\${transactionId}" to transactionId
        )
        val htmlContent = replacePlaceholders(template, placeholders)
        sendEmail(to, subject, htmlContent)
    }

    @Async("taskExecutor")
    fun sendSubscriptionConfirmationEmail(to: String, courseName: String, userName : String) {
        val subject = "Subscription Confirmation"
        val placeholders = mapOf(
            "\${courseName}" to courseName,
            "\${user}" to userName
        )
        val template = loadTemplate("src/main/resources/templates/subscription_complete.html")
        val htmlContent = replacePlaceholders(template, placeholders)
        sendEmail(to, subject, htmlContent)
    }

    @Async("taskExecutor")
    fun sendCreditsLoadedEmail(to: String, credits: Double, userName : String) {
        val subject = "Credits Loaded"
        val template = loadTemplate("src/main/resources/templates/credits_loaded.html")
        val placeholders = mapOf(
            "\${credits}" to credits.toString(),
            "\${user}" to userName
        )
        val htmlContent = replacePlaceholders(template, placeholders)
        sendEmail(to, subject, htmlContent)
    }
}


@Service
@ConditionalOnProperty(name = ["email.enabled"], havingValue = "false", matchIfMissing = true)
class NoOpEmailService(mailSender: JavaMailSender) : EmailService(mailSender) {
    override fun sendEmail(to: String, subject: String, body: String) {}
    override fun sendPaymentConfirmationEmail(to: String, amount: Double, userName: String, transactionId: String) {}
    override fun sendSubscriptionConfirmationEmail(to: String, courseName: String, userName: String) {}
    override fun sendCreditsLoadedEmail(to: String, credits: Double, userName: String) {}
}
