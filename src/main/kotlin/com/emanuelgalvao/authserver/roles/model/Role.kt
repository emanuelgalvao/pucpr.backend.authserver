package com.emanuelgalvao.authserver.roles.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

@Entity
@Table(name = "roles")
class Role(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var name: String = "",
    var description: String = ""
)