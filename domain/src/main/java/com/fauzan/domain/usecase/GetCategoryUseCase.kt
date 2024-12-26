package com.fauzan.domain.usecase

import com.fauzan.domain.repository.CategoryRepository

class GetCategoryUseCase (private val repository: CategoryRepository) {
    suspend fun execute() = repository.getCategories()
}