package com.fauzan.domain.repository

import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.model.CartModel
import com.fauzan.domain.model.CartSummary
import com.fauzan.domain.model.request.AddCartRequestModel
import com.fauzan.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>

    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
}