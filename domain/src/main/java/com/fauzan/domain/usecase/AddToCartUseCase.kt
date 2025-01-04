package com.fauzan.domain.usecase

import com.fauzan.domain.model.request.AddCartRequestModel
import com.fauzan.domain.repository.CartRepository

class AddToCartUseCase (private val cartRepository: CartRepository) {
    suspend fun execute(request: AddCartRequestModel) = cartRepository.addProductToCart(request)
}