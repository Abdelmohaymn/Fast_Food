package com.example.fastfood.model.recipesList


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Us(
    @SerializedName("amount")
    val amount: Double? = null,
    @SerializedName("unitLong")
    val unitLong: String? = null,
    @SerializedName("unitShort")
    val unitShort: String? = null
): Serializable