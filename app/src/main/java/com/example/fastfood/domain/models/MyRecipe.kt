package com.example.fastfood.domain.models


import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.ExtendedIngredient
import java.io.Serializable

data class MyRecipe(
    val analyzedInstructions: List<AnalyzedInstruction?>? = null,
    val cuisines: List<String?>? = null,
    val dishTypes: List<String?>? = null,
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    val image: String? = null,
    val instructions: String? = null,
    val summary: String? = null,
    val title: String? = null,
    val id:Int?=null
) : Serializable