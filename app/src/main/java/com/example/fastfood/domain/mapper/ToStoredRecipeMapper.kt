package com.example.fastfood.domain.mapper

import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.roomDb.RecipeEntity
import com.example.fastfood.roomDb.StoredRecipe

class ToStoredRecipeMapper : BaseMapper<Recipe,StoredRecipe>() {
    override fun map(input: Recipe): StoredRecipe {
        return StoredRecipe(
            id = input.id?:0,
            timestamp = System.currentTimeMillis(),
            recipe = RecipeEntity(
                analyzedInstructions = input.analyzedInstructions,
                cuisines = input.cuisines,
                dishTypes = input.dishTypes,
                extendedIngredients = input.extendedIngredients,
                image = input.image,
                instructions = input.instructions,
                summary = input.summary,
                title = input.title
            )
        )
    }
}