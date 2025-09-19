package com.ronaimate.hexagonal.infra.jpa

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring Data JPA repository for managing [UserEntity] persistence.
 *
 * This interface provides CRUD operations for [UserEntity] objects,
 * using the user's name as the primary key. It is used by the outbound adapter
 * to persist domain-level [User] data in a relational database.
 *
 * By extending [JpaRepository], it inherits standard JPA functionality
 * such as `save`, `findById`, and `delete`.
 */
interface UserJPARepository : JpaRepository<UserEntity, String>

/**
 * JPA entity representing a persisted user record.
 *
 * This class maps to a database table and is used exclusively within the
 * infrastructure layer. It mirrors the domain-level [User] model but may
 * include additional annotations or fields required by the persistence mechanism.
 *
 * @property name The unique identifier for the user (used as primary key).
 * @property age The age of the user.
 */
@Entity
data class UserEntity(
    @Id val name: String,
    val age: Int
)
