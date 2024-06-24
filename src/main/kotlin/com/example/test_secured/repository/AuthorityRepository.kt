package com.example.test_secured.repository

import com.example.test_secured.entities.Authority
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository: CrudRepository<Authority, String> {
    fun findByName(name: String): Authority?
}
