package com.example.fastfood.model.complexSearch


import com.google.gson.annotations.SerializedName

data class ComplexSearchResponse(
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val results: List<RecipeFromSearch?>? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
)