package com.ronaimate.hexagonal.common

import com.ronaimate.hexagonal.domain.model.User
import com.ronaimate.hexagonal.domain.port.out.UserRepositoryPort

class InMemoryUserRepository : UserRepositoryPort {
    val savedUsers = mutableListOf<User>()

    override fun saveUser(user: User) {
        savedUsers.add(user)
    }
}