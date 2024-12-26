package com.fauzan.data.repository

import com.fauzan.domain.model.Product
import com.fauzan.domain.network.NetworkService
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.repository.ProductRepository

class ProductRepositoryImpl(private val networkService: NetworkService) : ProductRepository {
    override suspend fun getProducts(category: String?): ResultWrapper<List<Product>> {
        return networkService.getProducts(category)
    }
}