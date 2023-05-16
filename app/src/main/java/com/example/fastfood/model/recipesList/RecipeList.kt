package com.example.fastfood.model.recipesList


import com.google.gson.annotations.SerializedName


data class RecipeList(
    @SerializedName("recipes")
    val recipes: List<Recipe?>? = null
)