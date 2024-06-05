package ar.edu.unsam.pds.dto.exceptions

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = ["timestamp", "status", "error", "message", "details", "path"])
class BodyResponse(
    @JsonIgnore val statusCode: HttpStatusCode,
    @JsonIgnore val  request: WebRequest,

    @JsonProperty val  message: String?,
) {
    @JsonProperty private val timestamp: LocalDateTime = LocalDateTime.now()
    @JsonProperty private val status: Int = statusCode.value()
    @JsonProperty private val error: String = HttpStatus.valueOf(statusCode.value()).reasonPhrase
    @JsonProperty private val path: String = request.getDescription(false).replace("uri=", "")
}