package com.fauzan.domain.di

import com.fauzan.domain.usecase.AddToCartUseCase
import com.fauzan.domain.usecase.CartSummaryUseCase
import com.fauzan.domain.usecase.DeleteProductUseCase
import com.fauzan.domain.usecase.GetCartUseCase
import com.fauzan.domain.usecase.GetCategoryUseCase
import com.fauzan.domain.usecase.GetProductUseCase
import com.fauzan.domain.usecase.UpdateQuantityUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoryUseCase(get()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartUseCase(get()) }
    factory { UpdateQuantityUseCase(get()) }
    factory { DeleteProductUseCase(get()) }
    factory { CartSummaryUseCase(get()) }
}