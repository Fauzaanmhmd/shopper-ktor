package com.fauzan.domain.di

import com.fauzan.domain.usecase.GetCategoryUseCase
import com.fauzan.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
}