package com.emanuelgalvao.authserver.roles

import com.emanuelgalvao.authserver.roles.model.Role
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {

    fun save(role: Role): Role =
        roleRepository.save(role)

    fun findAll(): List<Role> =
        roleRepository.findAll(Sort.by("name").ascending())
}