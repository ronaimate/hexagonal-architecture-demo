package com.ronaimate.hexagonal.domain.businesslogic

import com.ronaimate.hexagonal.domain.model.User
import com.ronaimate.hexagonal.domain.port.out.UserRepositoryPort

/**
 * Use-case responsible for saving a user if they meet the business criteria.
 *
 * This class encapsulates the application logic for persisting a user,
 * delegating the actual storage to the [UserRepositoryPort].
 *
 * The business rule enforced here is that only users who are considered adults
 * (i.e., age ≥ 18) will be saved.
 *
 * @property userRepositoryPort The output port used to persist user data.
 */
class SaveUserUseCase(private val userRepositoryPort: UserRepositoryPort) {

    /**
     * Executes the save operation for the given [user].
     *
     * If the user is an adult, the user will be passed to the [userRepositoryPort]
     * for persistence. Otherwise, no action is taken.
     *
     * @param user The user to be evaluated and potentially saved.
     */
    fun execute(user: User) {
        if (user.isAdult()) {
            userRepositoryPort.saveUser(user)
        }
    }
}
