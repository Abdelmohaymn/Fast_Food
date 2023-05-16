package com.example.fastfood.adapters

import com.example.fastfood.BR
import com.example.fastfood.R
import com.example.fastfood.model.recipesList.ExtendedIngredient

class IngredientAdapter(items:List<ExtendedIngredient>):BaseAdapter<ExtendedIngredient>(items,null) {

    override val layoutId = R.layout.ingredient_item

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val myHolder = holder as ItemViewHolder
        myHolder.binding.apply {
            setVariable(BR.status,true)
        }
    }

}