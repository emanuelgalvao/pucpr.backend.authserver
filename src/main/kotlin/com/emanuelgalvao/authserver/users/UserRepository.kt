package com.emanuelgalvao.authserver.users

import com.emanuelgalvao.authserver.users.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long>