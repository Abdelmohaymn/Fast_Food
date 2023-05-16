package com.example.fastfood.model.recipesList


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnalyzedInstruction(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("steps")
    val steps: List<Step?>? = null
): Serializable