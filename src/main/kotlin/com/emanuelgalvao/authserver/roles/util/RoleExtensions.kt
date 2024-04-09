package com.emanuelgalvao.authserver.roles.util

import com.emanuelgalvao.authserver.roles.model.Role
import com.emanuelgalvao.authserver.roles.model.RoleRequest
import com.emanuelgalvao.authserver.roles.model.RoleResponse

fun RoleRequest.toRole(): Role = Role(
    name = this.name,
    description = this.description
)

fun Role.toResponse(): RoleResponse = RoleResponse(
    name = this.name,
    description = this.description
)