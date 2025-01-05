package com.fauzan.data.model.response

import com.fauzan.domain.model.CartSummary
import kotlinx.serialization.Serializable

@Serializable
data class CartSummaryResponse(
    val `data`: Summary,
    val msg: String
) {
    fun toCartSummary() = CartSummary(
        data = `data`.toCartSummaryData(),
        msg = msg
    )
}
