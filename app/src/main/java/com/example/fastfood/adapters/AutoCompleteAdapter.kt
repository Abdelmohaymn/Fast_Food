package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.model.autoComplete.AutoCompleteResponseItem

class AutoCompleteAdapter(items:List<AutoCompleteResponseItem>, myListener:ItemsInteraction,
):BaseAdapter<AutoCompleteResponseItem>(items,myListener) {

    override val layoutId = R.layout.auto_complete_item

    interface ItemsInteraction : BaseItemsInteraction{
        fun onClickOnAutoComItem(query:String)
        fun onClickOnTopLeft(query:String)
    }
}