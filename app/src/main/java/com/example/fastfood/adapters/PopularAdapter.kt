package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.model.recipesList.Recipe

class PopularAdapter(items:List<Recipe>, myListener:ItemsInteraction):BaseAdapter<Recipe>(items,myListener) {
    override val layoutId: Int = R.layout.popular_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnPopularItem(item: Recipe)
    }

}