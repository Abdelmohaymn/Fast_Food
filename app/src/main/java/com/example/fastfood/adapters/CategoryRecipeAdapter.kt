package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.model.recipesList.Recipe

class CategoryRecipeAdapter(items:List<Recipe>,myListener:ItemsInteraction):BaseAdapter<Recipe>(items,myListener) {

    override val layoutId = R.layout.category_recipe_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnCategoryRecipeItem(item: Recipe)
    }
}