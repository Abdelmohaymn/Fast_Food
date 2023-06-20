package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.FavRecipe
import java.util.*


class ToFavRecipeMapper: BaseMapper<MyRecipe,FavRecipe>()  {
    override fun map(input: MyRecipe): FavRecipe {
        return FavRecipe(
            analyzedInstructions = input.analyzedInstructions,
            cuisines = input.cuisines,
            dishTypes = input.dishTypes,
            extendedIngredients = input.extendedIngredients,
            image = input.image,
            instructions = input.instructions,
            summary = input.summary,
            title = input.title,
            id = input.id?:0,
            date = Date()
        )
    }
}