package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.similarRecipes.SimilarRecipesItem

class FavoritesAdapter(items:List<MyRecipe>,myListener:ItemsInteraction)
    : BaseAdapter<MyRecipe>(items,myListener){

    override val layoutId = R.layout.fav_recipe_item

    interface ItemsInteraction : BaseAdapter.BaseItemsInteraction {
        fun onClickOnFavRecipeItem(item: MyRecipe)
    }

}