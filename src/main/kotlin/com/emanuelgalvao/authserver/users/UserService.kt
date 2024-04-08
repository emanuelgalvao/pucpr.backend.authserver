package com.emanuelgalvao.authserver.users

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun save(user: User): User =
        userRepository.save(user)

    fun findAll(sortDirection: SortDirection): List<User> =
        userRepository.findAll(sortDirection)

    fun findByIdOrNull(id: Long): User? =
        userRepository.findByIdOrNull(id)

    fun remove(id: Long): Boolean =
        userRepository.remove(id)
}