package com.fauzan.navigation

import com.fauzan.model.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
data class ProductDetails(val product: UiProductModel)