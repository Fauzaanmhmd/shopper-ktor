package com.fauzan.data.model.response

import com.fauzan.data.model.DataProductModel
import com.fauzan.domain.model.ProductListModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val `data`: List<DataProductModel>,
    val msg: String
) {
    fun toProductList() = ProductListModel (
        products = `data`.map { it.toProduct() },
        msg = msg
    )
}
