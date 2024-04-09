package com.emanuelgalvao.authserver.roles

import com.emanuelgalvao.authserver.roles.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, Long> {

    fun findByName(name: String): Role?
}