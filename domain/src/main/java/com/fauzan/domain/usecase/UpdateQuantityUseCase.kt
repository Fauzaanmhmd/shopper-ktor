package com.fauzan.domain.usecase

import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.repository.CartRepository

class UpdateQuantityUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(cartItemModel: CartItemModel) = cartRepository.updateQuantity(cartItemModel)
}