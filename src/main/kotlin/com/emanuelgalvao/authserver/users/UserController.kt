package com.emanuelgalvao.authserver.users

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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
}