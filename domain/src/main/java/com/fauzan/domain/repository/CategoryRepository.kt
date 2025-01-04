package com.fauzan.domain.repository

import com.fauzan.domain.model.CategoriesListModel
import com.fauzan.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories() : ResultWrapper<CategoriesListModel>
}