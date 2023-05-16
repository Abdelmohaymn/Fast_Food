package com.example.fastfood.model.recipesList


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Step(
    @SerializedName("equipment")
    val equipment: List<Equipment?>? = null,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient?>? = null,
    @SerializedName("length")
    val length: Length? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("step")
    val step: String? = null
): Serializable