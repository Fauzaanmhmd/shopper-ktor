package com.fauzan.domain.repository

import com.fauzan.domain.model.ProductListModel
import com.fauzan.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category: Int?) : ResultWrapper<ProductListModel>
}