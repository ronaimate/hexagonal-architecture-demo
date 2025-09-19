package com.ronaimate.hexagonal.domain.port.out

import com.ronaimate.hexagonal.domain.model.User

/**
 * Output port for persisting user data.
 *
 * This interface defines the contract for saving a [User] to a data store.
 * It is part of the hexagonal architecture's outbound communication,
 * allowing the domain or application layer to remain decoupled from specific
 * infrastructure technologies such as databases or external services.
 *
 * Implementations of this port may use JPA, JDBC, file systems, or any other
 * persistence mechanism, but those details are hidden from the core logic.
 */
interface UserRepositoryPort {

    /**
     * Persists the given [user] to the underlying data store.
     *
     * The implementation is responsible for handling the actual storage logic.
     * This method is typically invoked by a use-case after business validation.
     *
     * @param user The user entity to be saved.
     */
    fun saveUser(user: User)
}
