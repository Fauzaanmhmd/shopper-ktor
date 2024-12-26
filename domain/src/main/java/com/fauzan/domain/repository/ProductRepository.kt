package com.fauzan.domain.repository

import com.fauzan.domain.model.Product
import com.fauzan.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category: String?) : ResultWrapper<List<Product>>
}