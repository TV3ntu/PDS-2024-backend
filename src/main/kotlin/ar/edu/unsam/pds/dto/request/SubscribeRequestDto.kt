package ar.edu.unsam.pds.dto.request

import org.hibernate.validator.constraints.UUID

data class SubscribeRequestDto(@UUID val idUser: String, @UUID val idAssignment: String)