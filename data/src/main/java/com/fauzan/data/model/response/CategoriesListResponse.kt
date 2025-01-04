package com.fauzan.data.model.response

import com.fauzan.data.model.DataCategoryModel
import com.fauzan.domain.model.CategoriesListModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesListResponse (
    val `data`: List<DataCategoryModel>,
    val msg: String
) {
    fun toCategoriesList() = CategoriesListModel(
        categories = `data`.map { it.toCategory() },
        msg = msg
    )
}