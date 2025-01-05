package com.fauzan.domain.usecase

import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.repository.CartRepository

class DeleteProductUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemId: Int, userId: Int) = cartRepository.deleteItem(cartItemId, userId)
}