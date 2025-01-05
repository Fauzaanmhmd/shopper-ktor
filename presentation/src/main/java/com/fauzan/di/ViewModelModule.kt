package com.fauzan.di

import com.fauzan.ui.cart.CartViewModel
import com.fauzan.ui.home.HomeViewModel
import com.fauzan.ui.product_details.ProductDetailsViewModel
import com.fauzan.ui.summary.CartSummaryViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ProductDetailsViewModel(get())
    }
    viewModel {
        CartViewModel(get(), get(), get())
    }
    viewModel {
        CartSummaryViewModel(get())
    }
}