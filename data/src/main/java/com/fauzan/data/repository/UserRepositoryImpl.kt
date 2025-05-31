package com.fauzan.data.repository

import com.fauzan.domain.network.NetworkService
import com.fauzan.domain.repository.UserRepository

class UserRepositoryImpl(private val networkService: NetworkService) : UserRepository {
    override suspend fun login(
        email: String,
        password: String
    ) = networkService.login(email, password)

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ) = networkService.register(email, password, name)
}