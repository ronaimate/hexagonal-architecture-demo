package com.ronaimate.hexagonal.domain.model

/**
 * Represents a user within the domain model.
 *
 * This data class encapsulates the essential attributes of a user,
 * and provides domain-specific behavior such as age-based validation.
 *
 * @property name The name of the user.
 * @property age The age of the user, used for business rule evaluation.
 */
data class User(
    val name: String,
    val age: Int,
) {

    /**
     * Determines whether the user is considered an adult.
     *
     * This method enforces the business rule that adulthood begins at age 18.
     * It is used by application logic to decide whether certain operations
     * (e.g., persistence) should be allowed.
     *
     * @return `true` if the user's age is 18 or above, `false` otherwise.
     */
    fun isAdult(): Boolean = age >= 18
}
