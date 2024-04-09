package com.emanuelgalvao.authserver.roles

import com.emanuelgalvao.authserver.roles.model.RoleRequest
import com.emanuelgalvao.authserver.roles.model.RoleResponse
import com.emanuelgalvao.authserver.roles.util.toResponse
import com.emanuelgalvao.authserver.roles.util.toRole
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(
    private val roleService: RoleService
) {

    @PostMapping
    fun save(@RequestBody @Valid roleRequest: RoleRequest): RoleResponse =
        roleService.save(roleRequest.toRole()).toResponse()

    @GetMapping
    fun findAll(): List<RoleResponse> =
        roleService.findAll().map { it.toResponse() }
}