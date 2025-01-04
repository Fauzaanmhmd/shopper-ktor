package com.fauzan.ui.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.domain.model.request.AddCartRequestModel
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.AddToCartUseCase
import com.fauzan.model.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(val useCase: AddToCartUseCase) : ViewModel() {

    private val _state = MutableStateFlow<ProductDetailsEvent>(ProductDetailsEvent.Nothing)
    val state = _state.asStateFlow()

    fun addProductToCart(product: UiProductModel) {
        viewModelScope.launch {
            _state.value = ProductDetailsEvent.Loading
            val result = useCase.execute(
                AddCartRequestModel(
                    product.id,
                    product.title,
                    product.price,
                    1,
                    1
                )
            )
            when (result) {
                is ResultWrapper.Succsess -> {
                    _state.value = ProductDetailsEvent.Success("Product Add To Cart")
                }
                is ResultWrapper.Failure -> {
                    _state.value = ProductDetailsEvent.Failed("Something went wrong!")
                }
            }
        }
    }
}

sealed class ProductDetailsEvent {
    data object Loading : ProductDetailsEvent()
    data object Nothing : ProductDetailsEvent()
    data class Success(val message: String) : ProductDetailsEvent()
    data class Failed(val message: String) : ProductDetailsEvent()
}