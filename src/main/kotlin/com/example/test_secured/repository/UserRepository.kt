package com.example.test_secured.repository

import com.example.test_secured.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, String> {
    fun findByUsername(username: String): User?
}
