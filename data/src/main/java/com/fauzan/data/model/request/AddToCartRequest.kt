package com.fauzan.data.model.request

import com.fauzan.domain.model.request.AddCartRequestModel
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartRequest(
    val productId: Int,
    val quantity: Int,
    val productName: String,
    val price: Double,
    val userId: Int,
) {
    companion object {
        fun fromCartRequestModel(addCartRequestModel: AddCartRequestModel) = AddToCartRequest(
            productId = addCartRequestModel.productId,
            quantity = addCartRequestModel.quantity,
            productName = addCartRequestModel.productName,
            price = addCartRequestModel.price,
            userId = addCartRequestModel.userId,
        )
    }
}