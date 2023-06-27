package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.model.complexSearch.RecipeFromSearch

class FromSearchRecipeToMyMini:BaseMapper<RecipeFromSearch,MyMiniRecipe>() {
    override fun map(input: RecipeFromSearch): MyMiniRecipe {
        return MyMiniRecipe(
            id = input.id,
            image = input.image,
            title = input.title
        )
    }
}