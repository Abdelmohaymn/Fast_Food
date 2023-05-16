package com.example.fastfood.adapters

import com.example.fastfood.R

class CategoryAdapter(items:List<String>,myListener:ItemsInteraction):BaseAdapter<String>(items,myListener) {

    override val layoutId = R.layout.category_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnCategoryItem(item: String)
    }
}