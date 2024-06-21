package com.example.test_secured.entities

import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "users")
data class User(
    private var username: String,
    var givenname: String,
    var familyname: String,
    private var password: String,
    @Id val id: String = UUID.randomUUID().toString(),
    val enabled: Boolean = true,
    @ManyToMany(fetch = FetchType.EAGER) private var authorities: List<Authority> = mutableListOf(),
): UserDetails {

    override fun getAuthorities() = authorities
    override fun getPassword() = password
    override fun getUsername() = username
}
