package com.fauzan.domain.usecase

import com.fauzan.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend fun execute(category: Int?) = repository.getProducts(category)
}