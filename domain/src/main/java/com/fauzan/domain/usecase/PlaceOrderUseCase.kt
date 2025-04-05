package com.fauzan.domain.usecase

import com.fauzan.domain.model.AddressDomainModel
import com.fauzan.domain.repository.OrderRepository

class PlaceOrderUseCase(val orderRepository: OrderRepository) {
    suspend fun execute(addressDomainModel: AddressDomainModel) = orderRepository.placeOrder(addressDomainModel)
}