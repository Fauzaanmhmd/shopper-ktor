package com.fauzan.domain.usecase

import com.fauzan.domain.repository.CartRepository

class GetCartUseCase(val cartRepository: CartRepository) {
    suspend fun execute(userId: Long) = cartRepository.getCart(userId)
}