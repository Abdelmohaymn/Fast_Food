package com.example.fastfood.adapters

import com.example.fastfood.BR
import com.example.fastfood.R
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.model.similarRecipes.SimilarRecipesItem

class SimilarRecipeAdapter(items:List<MyMiniRecipe>,myListener:ItemsInteraction)
    :BaseAdapter<MyMiniRecipe>(items,myListener) {

    override val layoutId = R.layout.similar_recipe_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnSimilarRecipeItem(item: MyMiniRecipe)
    }
}