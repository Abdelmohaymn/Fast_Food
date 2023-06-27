package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.FavRecipe
import com.example.fastfood.roomDb.RecipeEntity
import java.util.*


class ToFavRecipeMapper: BaseMapper<MyRecipe,FavRecipe>()  {
    override fun map(input: MyRecipe): FavRecipe {
        return FavRecipe(
            id = input.id?:0,
            date = Date(),
            recipe = RecipeEntity(
                analyzedInstructions = input.analyzedInstructions,
                cuisines = input.cuisines,
                dishTypes = input.dishTypes,
                extendedIngredients = input.extendedIngredients,
                image = input.image,
                instructions = input.instructions,
                summary = input.summary,
                title = input.title,
            )
        )
    }
}