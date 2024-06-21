package com.example.test_secured.mappers

import com.example.test_secured.dtos.UserDto
import com.example.test_secured.entities.User

fun User.toUserDto() = UserDto(
    username,
    givenname,
    familyname,
)
