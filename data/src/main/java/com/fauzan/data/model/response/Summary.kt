package com.fauzan.data.model.response

import com.fauzan.domain.model.SummaryData
import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val discount: Double,
    val items: List<CartItem>,
    val shipping: Double,
    val subtotal: Double,
    val tax: Double,
    val total: Double
) {
    fun toCartSummaryData(): SummaryData {
        return SummaryData(
            discount = discount,
            items = items.map { it.toCartItemModel() },
            shipping = shipping,
            subtotal = subtotal,
            tax = tax,
            total = total
        )
    }
}
