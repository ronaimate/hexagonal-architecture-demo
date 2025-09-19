package com.ronaimate.hexagonal.infra.adapter.out.persistance

import com.ronaimate.hexagonal.domain.model.User
import com.ronaimate.hexagonal.domain.port.out.UserRepositoryPort
import com.ronaimate.hexagonal.infra.jpa.UserEntity
import com.ronaimate.hexagonal.infra.jpa.UserJPARepository

/**
 * Converts a domain-level [User] object into a persistence-layer [UserEntity].
 *
 * This extension function is used to bridge the gap between the domain model
 * and the infrastructure-specific entity representation, typically used by JPA.
 *
 * @return A [UserEntity] containing the same name and age as the domain [User].
 */
fun User.toEntity(): UserEntity = UserEntity(this.name, this.age)

/**
 * Adapter that implements the [UserRepositoryPort] using a JPA-based repository.
 *
 * This class serves as an outbound adapter in the hexagonal architecture,
 * translating domain-level [User] objects into [UserEntity] instances and
 * delegating persistence operations to the [UserJPARepository].
 *
 * By encapsulating the JPA logic here, the domain and application layers remain
 * decoupled from infrastructure concerns.
 *
 * @property userJpaRepository The Spring Data JPA repository used to persist entities.
 */
class UserRepositoryPortAdapter(
    private val userJpaRepository: UserJPARepository
) : UserRepositoryPort {

    /**
     * Saves the given [User] by converting it to a [UserEntity] and delegating
     * the operation to the underlying JPA repository.
     *
     * @param user The domain user to be persisted.
     */
    override fun saveUser(user: User) {
        userJpaRepository.save(user.toEntity())
    }
}
