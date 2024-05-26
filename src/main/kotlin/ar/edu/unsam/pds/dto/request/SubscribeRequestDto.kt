package ar.edu.unsam.pds.dto.request

import org.hibernate.validator.constraints.UUID

data class SubscribeRequestDto(
    @field: UUID(message = "Debe ser un UUID válido")
    val idUser: String,

    @field: UUID(message = "Debe ser un UUID válido")
    val idAssignment: String
)