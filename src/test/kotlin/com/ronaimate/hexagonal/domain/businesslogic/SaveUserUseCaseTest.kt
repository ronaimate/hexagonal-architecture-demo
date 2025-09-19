package com.ronaimate.hexagonal.domain.businesslogic

import com.ronaimate.hexagonal.domain.businesslogic.SaveUserUseCase
import com.ronaimate.hexagonal.domain.model.User
import com.ronaimate.hexagonal.common.InMemoryUserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SaveUserUseCaseTest {

    @Test
    fun `should save adult user`() {
        val repository = InMemoryUserRepository()
        val useCase = SaveUserUseCase(repository)

        val user = User(name = "Anna", age = 20)
        useCase.execute(user)

        assertEquals(1, repository.savedUsers.size)
        assertEquals(user, repository.savedUsers.first())
    }

    @Test
    fun `should not save underage user`() {
        val repository = InMemoryUserRepository()
        val useCase = SaveUserUseCase(repository)

        val user = User(name = "Tom", age = 16)
        useCase.execute(user)

        assertTrue(repository.savedUsers.isEmpty())
    }
}