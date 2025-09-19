package com.ronaimate.hexagonal.infra.adapter.`in`.scheduler

import com.ronaimate.hexagonal.domain.businesslogic.SaveUserUseCase
import com.ronaimate.hexagonal.domain.model.User
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import kotlin.random.Random

/**
 * Input adapter responsible for periodically generating and saving random users.
 *
 * This component acts as a scheduled job within the hexagonal architecture,
 * triggering the [SaveUserUseCase] at fixed intervals with randomly generated user data.
 *
 * The generated users have randomized names and ages, and are evaluated by the business logic
 * before being persisted.
 *
 * @property saveUserUseCase The use-case responsible for validating and saving users.
 */
@Component
class UserScheduler(val saveUserUseCase: SaveUserUseCase) {

    /**
     * Scheduled task that creates and attempts to save a randomly generated user.
     *
     * This method runs every minute and generates a user with:
     * - A random full name (first + last)
     * - A random age between 15 and 30
     *
     * Only users who meet the business criteria (e.g., age ≥ 18) will be persisted,
     * as enforced by the [SaveUserUseCase].
     */
    @Scheduled(cron = "\${user-scheduler.cron}")
    fun createRandomUser() {
        saveUserUseCase.execute(User(generateRandomFullName(), Random.nextInt(15, 30)))
    }

    /**
     * Generates a random full name composed of a first and last name.
     *
     * Names are selected from predefined English-language lists to resemble realistic user names.
     * This method is used to simulate incoming user data in a controlled, repeatable way.
     *
     * @return A string representing a full name (e.g., "Emma Davis").
     */
    private fun generateRandomFullName(): String {
        val firstNames = listOf(
            "James", "Olivia", "Liam", "Emma", "Noah", "Ava", "William", "Sophia",
            "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Charlotte", "Alexander", "Amelia"
        )

        val lastNames = listOf(
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
            "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas"
        )

        val firstName = firstNames.random()
        val lastName = lastNames.random()

        return "$firstName $lastName"
    }
}
