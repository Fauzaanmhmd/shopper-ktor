package com.fauzan.domain.usecase

import com.fauzan.domain.repository.CartRepository

class CartSummaryUseCase(private val repository: CartRepository) {
    suspend fun execute(userId: Int) = repository.getCartSummary(userId)
}