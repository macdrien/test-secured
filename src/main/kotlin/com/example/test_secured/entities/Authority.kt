package com.example.test_secured.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity
data class Authority(
    @Id @Column(unique = true) val name: String,
): GrantedAuthority {

    override fun getAuthority() = name
}
