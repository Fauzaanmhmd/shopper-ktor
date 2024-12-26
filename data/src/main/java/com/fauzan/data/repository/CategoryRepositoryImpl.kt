package com.fauzan.data.repository

import com.fauzan.domain.network.NetworkService
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.repository.CategoryRepository

class CategoryRepositoryImpl(val networkService: NetworkService) : CategoryRepository {
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return networkService.getCategories()
    }
}