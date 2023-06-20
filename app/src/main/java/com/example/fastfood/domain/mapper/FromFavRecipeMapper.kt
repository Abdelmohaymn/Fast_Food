package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.FavRecipe

class FromFavRecipeMapper:BaseMapper<FavRecipe,MyRecipe>() {
    override fun map(input: FavRecipe): MyRecipe {
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