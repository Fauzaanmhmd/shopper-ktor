package com.fauzan.domain.usecase

import com.fauzan.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    suspend fun execute(username: String, password: String) = userRepository.login(username, password)
}