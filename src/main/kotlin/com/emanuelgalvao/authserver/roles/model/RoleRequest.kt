package com.emanuelgalvao.authserver.roles.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class RoleRequest(
    @field:Pattern(regexp = "^[A-Z][A-Z0-9]+$")
    val name: String,
    @field:NotBlank
    val description: String
)