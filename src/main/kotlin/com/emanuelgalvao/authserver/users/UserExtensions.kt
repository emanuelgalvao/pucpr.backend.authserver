package com.emanuelgalvao.authserver.users

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