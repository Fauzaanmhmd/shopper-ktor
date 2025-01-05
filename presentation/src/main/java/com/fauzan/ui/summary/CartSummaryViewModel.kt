package com.fauzan.ui.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.domain.model.CartSummary
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.CartSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartSummaryViewModel(private val cartSummaryUseCase: CartSummaryUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<CartSummaryEvent>(CartSummaryEvent.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCartSummary(1)
    }

    fun getCartSummary(userId: Int) {
        viewModelScope.launch {
            _uiState.value = CartSummaryEvent.Loading
            val summary = cartSummaryUseCase.execute(userId)

            when (summary) {
                is ResultWrapper.Succsess -> {
                    _uiState.value = CartSummaryEvent.Success(summary.value)
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
}