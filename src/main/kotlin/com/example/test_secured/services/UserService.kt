package com.example.test_secured.services

import com.example.test_secured.dtos.RegisterUser
import com.example.test_secured.entities.User
import com.example.test_secured.mappers.toUser
import com.example.test_secured.repository.AuthorityRepository
import com.example.test_secured.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val repository: UserRepository,
                  private val authorityRepository: AuthorityRepository,
                  private val passwordEncoder: PasswordEncoder): UserDetailsService {

    fun findUserByUsername(username: String?): User {
        if (username == null) {
            throw UsernameNotFoundException("Username can't be null")
        }
        return repository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }

    override fun loadUserByUsername(username: String?): UserDetails = findUserByUsername(username)

    fun registerUser(registerUser: RegisterUser): User {
        val authority = authorityRepository.findByName("user")
            ?: throw IllegalStateException("Expected authority not found")

        var user: User = registerUser.toUser(
            id = UUID.randomUUID().toString(),
            encodedPassword = passwordEncoder.encode(registerUser.password),
            authorities = listOf(authority),
        )
        user = repository.save(user)

        return user
    }
}
