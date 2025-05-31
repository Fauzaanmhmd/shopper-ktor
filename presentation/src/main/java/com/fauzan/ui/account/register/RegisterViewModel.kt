package com.fauzan.ui.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.ShopperSession
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val shopperSession: ShopperSession
) : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState = _registerState

    fun register(email: String, password: String, name: String) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            val response = registerUseCase.execute(email, password, name)

            when (response) {
                is ResultWrapper.Success -> {
                    shopperSession.storeUser(response.value)
                    _registerState.value = RegisterState.Success()
                }

                is ResultWrapper.Failure -> {
                    _registerState.value = RegisterState.Error(
                        response.exception.message ?: "Something went wront!"
                    )
                }
            }
        }
    }
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    class Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}