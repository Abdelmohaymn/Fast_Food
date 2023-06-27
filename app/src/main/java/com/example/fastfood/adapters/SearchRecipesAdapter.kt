package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.domain.models.MyMiniRecipe

class SearchRecipesAdapter(items:List<MyMiniRecipe>, myListener: ItemsInteraction,
) :BaseAdapter<MyMiniRecipe>(items,myListener){

    override val layoutId = R.layout.search_recipe_item

    interface ItemsInteraction:BaseItemsInteraction {
        fun onClickOnSearchResItem(item:MyMiniRecipe)
    }
}