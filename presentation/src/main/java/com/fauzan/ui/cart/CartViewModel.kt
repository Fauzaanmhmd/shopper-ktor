package com.fauzan.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.domain.model.CartItemModel
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.DeleteProductUseCase
import com.fauzan.domain.usecase.GetCartUseCase
import com.fauzan.domain.usecase.UpdateQuantityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    val cartUseCase: GetCartUseCase,
    private val updateQuantityUseCase: UpdateQuantityUseCase,
    private val deleteItem: DeleteProductUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartEvent>(CartEvent.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCart()
    }

    fun getCart() {
        viewModelScope.launch {
            _uiState.value = CartEvent.Loading
            cartUseCase.execute().let { result ->
                when (result) {
                    is ResultWrapper.Succsess -> {
                        _uiState.value = CartEvent.Success(result.value.data)
                    }

                    is ResultWrapper.Failure -> {
                        _uiState.value = CartEvent.Failed("Something went wrong!")
                    }
                }
            }
        }
    }

    fun incrementQuantity(cartItem: CartItemModel) {
        if (cartItem.quantity == 10) return
        updateQuantity(cartItem.copy(quantity = cartItem.quantity + 1))
    }

    fun decrementQuantity(cartItem: CartItemModel) {
        if (cartItem.quantity == 10) return
        updateQuantity(cartItem.copy(quantity = cartItem.quantity - 1))
    }

    fun updateQuantity(cartItem: CartItemModel) {
        viewModelScope.launch {
            _uiState.value = CartEvent.Loading
            val result = updateQuantityUseCase.execute(cartItem)
            when (result) {
                is ResultWrapper.Succsess -> {
                    _uiState.value = CartEvent.Success(result.value.data)
                }

                is ResultWrapper.Failure -> {
                    _uiState.value = CartEvent.Failed("Something went wront!")
                }
            }
        }

    }

    fun removeItem(cartItem: CartItemModel) {
        viewModelScope.launch {
            _uiState.value = CartEvent.Loading
            val result = deleteItem.execute(cartItem.id, 1)
            when (result) {
                is ResultWrapper.Succsess -> {
                    _uiState.value = CartEvent.Success(result.value.data)
                }

                is ResultWrapper.Failure -> {
                    _uiState.value = CartEvent.Failed("Something went wront!")
                }
            }
        }
    }
}

sealed class CartEvent {
    data object Loading : CartEvent()
    data class Success(val message: List<CartItemModel>) : CartEvent()
    data class Failed(val message: String) : CartEvent()
}