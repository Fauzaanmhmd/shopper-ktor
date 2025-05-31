package com.fauzan.ui.orders


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fauzan.ShopperSession
import com.fauzan.domain.model.OrdersData
import com.fauzan.domain.network.ResultWrapper
import com.fauzan.domain.usecase.OrderListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val orderListUseCase: OrderListUseCase,
    shopperSession: ShopperSession
) : ViewModel() {

    private val _ordersEvent = MutableStateFlow<OrdersEvent>(OrdersEvent.Loading)
    val ordersEvent = _ordersEvent.asStateFlow()
    val userDomainModel = shopperSession.getUser()
    val userId = userDomainModel?.id?.toLong()

    init {
        getOrderList()
    }

    fun filterOrders(list: List<OrdersData>, filter: String): List<OrdersData> {
        val filteredList = list.filter { it.status == filter }
        return filteredList
    }

    private fun getOrderList() {
        viewModelScope.launch {
            if (userId == null) {
                _ordersEvent.value = OrdersEvent.Error("User not found")
                return@launch
            }
            val result = orderListUseCase.execute(userId)

            when (result) {
                is ResultWrapper.Success -> {
                    val data = result.value
                    _ordersEvent.value = OrdersEvent.Success(data.`data`)
                }

                is ResultWrapper.Failure -> {
                    _ordersEvent.value = OrdersEvent.Error("Something went wrong")
                }
            }

        }
    }
}

sealed class OrdersEvent {
    object Loading : OrdersEvent()
    data class Success(val data: List<OrdersData>) : OrdersEvent()
    data class Error(val errorMsg: String) : OrdersEvent()
}