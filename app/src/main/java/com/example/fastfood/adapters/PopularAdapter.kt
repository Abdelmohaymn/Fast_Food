package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.Recipe

class PopularAdapter(items:List<MyRecipe>, myListener:ItemsInteraction):BaseAdapter<MyRecipe>(items,myListener) {
    override val layoutId: Int = R.layout.popular_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnPopularItem(item: MyRecipe)

        fun onLongClickOnPopularItem(item: MyRecipe):Boolean
    }

}