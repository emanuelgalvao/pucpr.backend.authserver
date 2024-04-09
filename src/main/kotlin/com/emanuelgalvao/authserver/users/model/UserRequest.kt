package com.emanuelgalvao.authserver.users.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank
    val name: String,
    @field:Email
    val email: String,
    val password: String
)
