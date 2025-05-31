package com.fauzan.utils

import java.text.NumberFormat
import java.util.Currency

object CurrencyUtils {
    val usdFormat: NumberFormat = NumberFormat.getCurrencyInstance().apply {
        currency = Currency.getInstance("USD")
    }
    fun formatPrice(price: Double): String = usdFormat.format(price)
}