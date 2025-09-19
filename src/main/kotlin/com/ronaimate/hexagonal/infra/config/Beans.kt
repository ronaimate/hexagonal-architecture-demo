package com.ronaimate.hexagonal.infra.config

import com.ronaimate.hexagonal.domain.businesslogic.SaveUserUseCase
import com.ronaimate.hexagonal.domain.port.out.UserRepositoryPort
import com.ronaimate.hexagonal.infra.adapter.out.persistance.UserRepositoryPortAdapter
import com.ronaimate.hexagonal.infra.jpa.UserJPARepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Central configuration class for wiring domain and infrastructure components.
 *
 * This class defines the application-level beans that connect the domain logic
 * to its infrastructure adapters, following the principles of hexagonal architecture.
 *
 * It ensures that the domain remains decoupled from technical concerns such as
 * persistence, while still enabling those concerns to be injected where needed.
 */
@Configuration
class AppConfig {

    /**
     * Registers a [UserRepositoryPort] bean backed by a JPA adapter.
     *
     * This function wraps the [UserJPARepository] in a [UserRepositoryPortAdapter],
     * allowing the domain layer to interact with the persistence layer via a clean interface.
     *
     * @param userJpaRepository The Spring Data JPA repository for [UserEntity] persistence.
     * @return A [UserRepositoryPort] implementation that delegates to JPA.
     */
    @Bean
    fun createUserRepositoryPort(userJpaRepository: UserJPARepository): UserRepositoryPort =
        UserRepositoryPortAdapter(userJpaRepository)

    /**
     * Registers the [SaveUserUseCase] bean, injecting the required output port.
     *
     * This use-case encapsulates the business logic for validating and saving users.
     * It depends on the [UserRepositoryPort] to persist data, allowing the logic to remain
     * infrastructure-agnostic.
     *
     * @param userRepositoryPort The output port used to persist validated users.
     * @return A fully constructed [SaveUserUseCase] instance.
     */
    @Bean
    fun createSaveUserUseCase(userRepositoryPort: UserRepositoryPort): SaveUserUseCase =
        SaveUserUseCase(userRepositoryPort)
}
