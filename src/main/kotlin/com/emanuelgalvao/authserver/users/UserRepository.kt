package com.emanuelgalvao.authserver.users

import org.springframework.stereotype.Component

@Component
class UserRepository {

    private var lastId  = 0L
    private val users: HashMap<Long, User> = hashMapOf()

    fun save(user: User): User {
        user.id?.let {
            users[it] = user
        } ?: run {
            lastId += 1
            user.id = lastId
            users[lastId] = user
        }
        return user
    }

    fun findAll(sortDirection: SortDirection): List<User> {
        return when (sortDirection) {
            SortDirection.ASC -> users.values.sortedBy { it.name }
            SortDirection.DESC -> users.values.sortedByDescending { it.name }
        }
    }

    fun findByIdOrNull(id: Long): User? =
        users[id]

    fun remove(id: Long): Boolean {
        return users[id]?.let {
            users.remove(id)
            true
        } ?: run {
            false
        }
    }
}