package com.emanuelgalvao.authserver.users.util

import com.emanuelgalvao.authserver.users.model.User
import com.emanuelgalvao.authserver.users.model.UserRequest
import com.emanuelgalvao.authserver.users.model.UserResponse

fun UserRequest.toUser(): User = User(
    name = this.name,
    email = this.email,
    password = this.password
)

fun User.toResponse(): UserResponse = UserResponse(
    id = this.id ?: 0L,
    name = this.name,
    email = this.email
)