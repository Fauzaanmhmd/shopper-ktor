package com.fauzan.data.repository

import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.model.CartModel
import com.fauzan.domain.model.CartSummary
import com.fauzan.domain.model.request.AddCartRequestModel
import com.fauzan.domain.network.NetworkService
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.repository.CartRepository

class CartRepositoryImpl (val networkService: NetworkService) : CartRepository {
    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): ResultWrapper<CartModel> {
        return networkService.getCart()
    }

    override suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel> {
        return networkService.updateQuantity(cartItemModel)
    }

    override suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel> {
        return networkService.deleteItem(cartItemId, userId)
    }

    override suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary> {
        return networkService.getCartSummary(userId)
    }
}