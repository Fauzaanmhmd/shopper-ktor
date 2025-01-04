package com.fauzan.di

import com.fauzan.ui.home.HomeViewModel
import com.fauzan.ui.product_details.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
}