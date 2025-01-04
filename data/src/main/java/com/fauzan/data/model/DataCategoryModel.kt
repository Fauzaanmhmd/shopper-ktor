package com.fauzan.data.model

import com.fauzan.domain.model.Category
import kotlinx.serialization.Serializable

@Serializable
data class DataCategoryModel(
    val id: Int,
    val image: String,
    val title: String
) {
    fun toCategory() = Category(
        id = id,
        image = image,
        title = title
    )
}
