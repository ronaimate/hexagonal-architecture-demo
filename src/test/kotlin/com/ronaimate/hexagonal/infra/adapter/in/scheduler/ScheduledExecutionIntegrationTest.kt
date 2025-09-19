package com.ronaimate.hexagonal.infra.adapter.`in`.scheduler

import com.ronaimate.hexagonal.HexagonalDemoApp
import com.ronaimate.hexagonal.domain.businesslogic.SaveUserUseCase
import com.ronaimate.hexagonal.domain.port.out.UserRepositoryPort
import com.ronaimate.hexagonal.infra.adapter.out.persistance.UserRepositoryPortAdapter
import com.ronaimate.hexagonal.infra.jpa.UserJPARepository
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit
import kotlin.test.Test

@SpringBootTest(classes = [HexagonalDemoApp::class])
class ScheduledExecutionIntegrationTest {

    @Autowired
    lateinit var userJpaRepository: UserJPARepository

    @Test
    fun `should automatically invoke scheduled method and persist adult user`() {
        await().atMost(10, TimeUnit.SECONDS).until {
            userJpaRepository.findAll().any { it.age >= 18 }
        }

        val allUsers = userJpaRepository.findAll()
        assertTrue(allUsers.all { it.age >= 18 })
    }

    @TestConfiguration
    class TestConfig {

        @Bean
        fun createUserRepositoryPort(userJpaRepository: UserJPARepository): UserRepositoryPort =
            UserRepositoryPortAdapter(userJpaRepository)

        @Bean
        fun createSaveUserUseCase(userRepositoryPort: UserRepositoryPort): SaveUserUseCase =
            SaveUserUseCase(userRepositoryPort)

        @Bean
        fun userScheduler(saveUserUseCase: SaveUserUseCase): UserScheduler =
            UserScheduler(saveUserUseCase)
    }
}
