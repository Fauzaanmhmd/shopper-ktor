package com.fauzan.navigation

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import com.fauzan.model.UiProductModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder

val productNavType = object : NavType<UiProductModel>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): UiProductModel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, UiProductModel::class.java)
        } else {
            bundle.getParcelable(key) as? UiProductModel
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun parseValue(value: String): UiProductModel {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: UiProductModel): String {
        return Json.encodeToString(value.copy(
            image = URLEncoder.encode(value.image, "UTF-8")
        ))
    }

    override fun put(bundle: Bundle, key: String, value: UiProductModel) {
        bundle.putParcelable(key, value)
    }
}
