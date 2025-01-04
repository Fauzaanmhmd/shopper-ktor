package com.fauzan.domain.repository

import com.fauzan.domain.model.CartModel
import com.fauzan.domain.model.request.AddCartRequestModel
import com.fauzan.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel
    ) : ResultWrapper<CartModel>
}