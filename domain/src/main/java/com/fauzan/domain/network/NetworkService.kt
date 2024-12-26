package com.fauzan.domain.network

import com.fauzan.domain.model.Product
import java.lang.Exception

interface NetworkService {
    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
    suspend fun getCategories(): ResultWrapper<List<String>>
}

sealed class ResultWrapper<out T> {
    data class Succsess<out T>(val value: T) : ResultWrapper<T>()
    data class Failure(val exception: Exception) : ResultWrapper<Nothing>()
}