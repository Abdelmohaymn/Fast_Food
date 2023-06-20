package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.Recipe

class RecipeMapper : BaseMapper<Recipe,MyRecipe>() {
    override fun map(input: Recipe): MyRecipe {
        return MyRecipe(
            analyzedInstructions = input.analyzedInstructions,
            cuisines = input.cuisines,
            dishTypes = input.dishTypes,
            extendedIngredients = input.extendedIngredients,
            image = input.image,
            instructions = input.instructions,
            summary = input.summary,
            title = input.title,
            id = input.id
        )
    }
}