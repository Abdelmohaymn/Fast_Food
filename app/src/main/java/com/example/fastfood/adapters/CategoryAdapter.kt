package com.example.fastfood.adapters

import com.example.fastfood.R

class CategoryAdapter(items:List<Pair<String,Int>>,myListener:ItemsInteraction):BaseAdapter<Pair<String,Int>>(items,myListener) {

    override val layoutId = R.layout.category_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnCategoryItem(item: Pair<String,Int>)
    }
}