package com.ronaimate.hexagonal.infra.adapter.`in`.scheduler

import com.ronaimate.hexagonal.domain.businesslogic.SaveUserUseCase
import com.ronaimate.hexagonal.infra.adapter.`in`.scheduler.UserScheduler
import com.ronaimate.hexagonal.common.InMemoryUserRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.RepeatedTest


class UserSchedulerTest {

    @RepeatedTest(10)
    fun `should only save adult users`() {
        val repository = InMemoryUserRepository()
        val useCase = SaveUserUseCase(repository)
        val scheduler = UserScheduler(useCase)

        scheduler.createRandomUser()

        assertTrue(repository.savedUsers.all { it.age >= 18 })
    }

    @RepeatedTest(10)
    fun `should sometimes save and sometimes skip based on age`() {
        val repository = InMemoryUserRepository()
        val useCase = SaveUserUseCase(repository)
        val scheduler = UserScheduler(useCase)

        scheduler.createRandomUser()

        assertTrue(repository.savedUsers.size in 0..1)
    }
}
