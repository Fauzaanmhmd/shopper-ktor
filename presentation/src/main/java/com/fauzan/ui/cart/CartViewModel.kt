package com.fauzan.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.ShopperSession
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
    private val deleteItem: DeleteProductUseCase,
    shopperSession: ShopperSession
) : ViewModel() {
    private val _uiState = MutableStateFlow<CartEvent>(CartEvent.Loading)
    val uiState = _uiState.asStateFlow()
    val userDomainModel = shopperSession.getUser()
    val userId = userDomainModel?.id?.toLong()

    init {
        getCart()
    }

    fun getCart() {
        viewModelScope.launch {
            if (userId == null) {
                _uiState.value = CartEvent.Failed("User not found")
                return@launch
            }
            _uiState.value = CartEvent.Loading
            cartUseCase.execute(userId).let { result ->
                when (result) {
                    is ResultWrapper.Success -> {
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
            if (userId == null) {
                _uiState.value = CartEvent.Failed("User not found")
                return@launch
            }
            _uiState.value = CartEvent.Loading
            val result = updateQuantityUseCase.execute(cartItem, userId)
            when (result) {
                is ResultWrapper.Success -> {
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
                is ResultWrapper.Success -> {
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