package com.fauzan.data.di

import com.fauzan.data.repository.CategoryRepositoryImpl
import com.fauzan.data.repository.ProductRepositoryImpl
import com.fauzan.domain.repository.CategoryRepository
import com.fauzan.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get())}
    single<CategoryRepository> { CategoryRepositoryImpl(get())}
}