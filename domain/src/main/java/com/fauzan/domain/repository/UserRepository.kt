package com.fauzan.domain.repository

import com.fauzan.domain.model.UserDomainModel
import com.fauzan.domain.network.ResultWrapper

interface UserRepository {
    suspend fun login(email: String, password: String) : ResultWrapper<UserDomainModel>
    suspend fun register(
        email: String,
        password: String,
        name: String
    ) : ResultWrapper<UserDomainModel>
}