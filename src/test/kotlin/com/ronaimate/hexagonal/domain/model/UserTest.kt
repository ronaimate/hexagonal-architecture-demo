package com.ronaimate.hexagonal.domain.model

import com.ronaimate.hexagonal.domain.model.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    fun `should return true when age is 18 or more`() {
        val user = User(name = "Anna", age = 18)
        assertTrue(user.isAdult())
    }

    @Test
    fun `should return false when age is under 18`() {
        val user = User(name = "Tom", age = 17)
        assertFalse(user.isAdult())
    }
}
