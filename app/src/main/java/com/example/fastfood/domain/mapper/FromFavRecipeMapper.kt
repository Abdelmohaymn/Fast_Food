package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.FavRecipe

class FromFavRecipeMapper:BaseMapper<FavRecipe,MyRecipe>() {
    override fun map(input: FavRecipe): MyRecipe {
        return MyRecipe(
            analyzedInstructions = input.recipe.analyzedInstructions,
            cuisines = input.recipe.cuisines,
            dishTypes = input.recipe.dishTypes,
            extendedIngredients = input.recipe.extendedIngredients,
            image = input.recipe.image,
            instructions = input.recipe.instructions,
            summary = input.recipe.summary,
            title = input.recipe.title,
            id = input.id
        )
    }

}