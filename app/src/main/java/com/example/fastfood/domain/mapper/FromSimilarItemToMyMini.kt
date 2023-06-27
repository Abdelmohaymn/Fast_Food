package com.example.fastfood.domain.mapper

import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.model.similarRecipes.SimilarRecipesItem

class FromSimilarItemToMyMini:BaseMapper<SimilarRecipesItem,MyMiniRecipe>() {
    override fun map(input: SimilarRecipesItem): MyMiniRecipe {
        return MyMiniRecipe(
            id = input.id,
            image = "https://spoonacular.com/recipeImages/${input.id}-556x370.${input.imageType}",
            title = input.title
        )
    }
}