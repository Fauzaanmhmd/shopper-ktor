package com.fauzan.domain.network

import com.fauzan.domain.model.AddressDomainModel
import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.model.CartModel
import com.fauzan.domain.model.CartSummary
import com.fauzan.domain.model.CategoriesListModel
import com.fauzan.domain.model.ProductListModel
import com.fauzan.domain.model.request.AddCartRequestModel

interface NetworkService {
    suspend fun getProducts(category: Int?): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>

    suspend fun addProductToCart(
        request: AddCartRequestModel
    ) : ResultWrapper<CartModel>

    suspend fun getCart() : ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteItem(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
    suspend fun placeOrder(address: AddressDomainModel, userId: Long): ResultWrapper<Long>
}

sealed class ResultWrapper<out T> {
    data class Succsess<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}