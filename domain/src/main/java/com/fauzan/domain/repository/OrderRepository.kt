package com.fauzan.domain.repository

import com.fauzan.domain.model.AddressDomainModel
import com.fauzan.domain.model.OrdersListModel
import com.fauzan.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel, userId: Long): ResultWrapper<Long>
    suspend fun getOrderList(userId: Long): ResultWrapper<OrdersListModel>
}