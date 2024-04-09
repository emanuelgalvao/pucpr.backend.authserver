package com.emanuelgalvao.authserver.users

import com.emanuelgalvao.authserver.users.model.UserRequest
import com.emanuelgalvao.authserver.users.model.UserResponse
import com.emanuelgalvao.authserver.users.util.toResponse
import com.emanuelgalvao.authserver.users.util.toSortDirection
import com.emanuelgalvao.authserver.users.util.toUser
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun save(@RequestBody @Valid userRequest: UserRequest): ResponseEntity<UserResponse> {
        val userResponse = userService.save(userRequest.toUser()).toResponse()
        return ResponseEntity(userResponse, HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(@RequestParam sortDir: String = "asc"): ResponseEntity<List<UserResponse>> {
        val sortDirection = sortDir.toSortDirection()
        return if (sortDirection == null) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok(
                userService.findAll(sortDirection).map {
                    it.toResponse()
                }
            )
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<UserResponse> {
        return userService.findByIdOrNull(id)?.let {
            ResponseEntity.ok(it.toResponse())
        } ?: run {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable("id") id: Long): ResponseEntity<Nothing> {
        return if (userService.remove(id)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
        }

    @PutMapping("/{id}/roles/{name}")
    fun addRole(@PathVariable("id") id: Long, @PathVariable("name") name: String): ResponseEntity<Nothing> {
        return if (userService.addRole(id, name))
            ResponseEntity.ok().build()
        else
            ResponseEntity.noContent().build()
    }
}