package com.example.fastfood.model.similarRecipes


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SimilarRecipesItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("imageType")
    val imageType: String? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = null,
    @SerializedName("servings")
    val servings: Int? = null,
    @SerializedName("sourceUrl")
    val sourceUrl: String? = null,
    @SerializedName("title")
    val title: String? = null
) : Serializable