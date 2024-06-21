package com.example.test_secured.services

import com.example.test_secured.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) {
            throw UsernameNotFoundException("Null username")
        }

        return repository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
    }
}
