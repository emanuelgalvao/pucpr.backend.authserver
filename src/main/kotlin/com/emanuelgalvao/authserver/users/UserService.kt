package com.emanuelgalvao.authserver.users

import com.emanuelgalvao.authserver.roles.RoleRepository
import com.emanuelgalvao.authserver.users.model.User
import com.emanuelgalvao.authserver.users.util.SortDirection
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
) {

    fun save(user: User): User =
        userRepository.save(user)

    fun findAll(sortDirection: SortDirection): List<User> {
        return when (sortDirection) {
            SortDirection.ASC ->
                userRepository.findAll(Sort.by("name").ascending())
            SortDirection.DESC ->
                userRepository.findAll(Sort.by("name").descending())
        }
    }

    fun findByIdOrNull(id: Long): User? =
        userRepository.findByIdOrNull(id)

    fun remove(id: Long): Boolean {
        return userRepository.findByIdOrNull(id)?.let {
            userRepository.deleteById(id)
            true
        } ?: false
    }

    fun addRole(userId: Long, roleName: String): Boolean {
        return userRepository.findByIdOrNull(userId)?.let { user ->
            roleRepository.findByName(roleName.uppercase())?.let { role ->
                if (user.roles.contains(role)) {
                    false
                } else {
                    user.roles.add(role)
                    userRepository.save(user)
                    true
                }
            } ?: throw IllegalArgumentException("Role ${roleName.uppercase()} Not Found")
        } ?: throw IllegalArgumentException("User $userId Not Found")
    }
}