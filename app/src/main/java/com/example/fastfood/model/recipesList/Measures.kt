package com.example.fastfood.model.recipesList


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Measures(
    @SerializedName("metric")
    val metric: Metric? = null,
    @SerializedName("us")
    val us: Us? = null
): Serializable