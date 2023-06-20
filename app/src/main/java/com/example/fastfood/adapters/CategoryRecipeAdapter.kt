package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.Recipe

class CategoryRecipeAdapter(items:List<MyRecipe>,myListener:ItemsInteraction):BaseAdapter<MyRecipe>(items,myListener) {

    override val layoutId = R.layout.category_recipe_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnCategoryRecipeItem(item: MyRecipe)
    }
}