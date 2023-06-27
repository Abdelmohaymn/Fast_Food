package com.example.fastfood.roomDb

import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.ExtendedIngredient

data class RecipeEntity(
    val analyzedInstructions: List<AnalyzedInstruction?>? = null,
    val cuisines: List<String?>? = null,
    val dishTypes: List<String?>? = null,
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    val image: String? = null,
    val instructions: String? = null,
    val summary: String? = null,
    val title: String? = null,
)