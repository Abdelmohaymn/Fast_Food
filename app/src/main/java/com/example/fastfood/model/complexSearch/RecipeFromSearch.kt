package com.example.fastfood.model.complexSearch


import com.google.gson.annotations.SerializedName

data class RecipeFromSearch(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("imageType")
    val imageType: String? = null,
    @SerializedName("title")
    val title: String? = null
)