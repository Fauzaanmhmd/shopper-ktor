package com.fauzan.ui.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.ShopperSession
import com.fauzan.domain.model.CartSummary
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.CartSummaryUseCase
import com.fauzan.domain.usecase.PlaceOrderUseCase
import com.fauzan.model.UserAddress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartSummaryViewModel(
    private val cartSummaryUseCase: CartSummaryUseCase,
    val placeOrderUseCase: PlaceOrderUseCase,
    shopperSession: ShopperSession
) : ViewModel() {

    private val _uiState = MutableStateFlow<CartSummaryEvent>(CartSummaryEvent.Loading)
    val uiState = _uiState.asStateFlow()
    val userDomainModel = shopperSession.getUser()
    val userId = userDomainModel?.id?.toLong()

    init {
        getCartSummary(userId)
    }

    fun getCartSummary(userId: Long?) {
        viewModelScope.launch {
            if (userId == null) {
                _uiState.value = CartSummaryEvent.Failed("User not found")
                return@launch
            }
            _uiState.value = CartSummaryEvent.Loading
            val summary = cartSummaryUseCase.execute(userId)

            when (summary) {
                is ResultWrapper.Success -> {
                    _uiState.value = CartSummaryEvent.Success(summary.value)
                }

                is ResultWrapper.Failure -> {
                    _uiState.value = CartSummaryEvent.Failed("Something went wrong!")
                }
            }
        }
    }

    fun placeOrder(userAddress: UserAddress) {
        viewModelScope.launch {
            if (userId == null) {
                _uiState.value = CartSummaryEvent.Failed("User not found")
                return@launch
            }
            _uiState.value = CartSummaryEvent.Loading
            val orderId = placeOrderUseCase.execute(userAddress.toAddressDataModel(), userId)

            when (orderId) {
                is ResultWrapper.Success -> {
                    _uiState.value = CartSummaryEvent.PlaceOrder(orderId.value)
                }

                is ResultWrapper.Failure -> {
                    _uiState.value = CartSummaryEvent.Failed("Something went wrong!")
                }
            }
        }
    }

}

sealed class CartSummaryEvent {
    data object Loading : CartSummaryEvent()
    data class Success(val summary: CartSummary) : CartSummaryEvent()
    data class Failed(val message: String) : CartSummaryEvent()
    data class PlaceOrder(val orderId: Long) : CartSummaryEvent()
}