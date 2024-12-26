package com.fauzan.domain.repository

import com.fauzan.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories() : ResultWrapper<List<String>>
}