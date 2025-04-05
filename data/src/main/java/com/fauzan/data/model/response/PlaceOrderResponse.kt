package com.fauzan.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class PlaceOrderResponse(
    val `data`: List<OrderD>,
    val msg: String
)
