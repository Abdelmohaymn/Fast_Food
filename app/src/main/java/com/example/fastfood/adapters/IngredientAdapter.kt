package com.example.fastfood.adapters


import com.example.fastfood.R
 import com.example.fastfood.model.recipesList.ExtendedIngredient

class IngredientAdapter(items:List<ExtendedIngredient>):BaseAdapter<ExtendedIngredient>(items,null) {

    override val layoutId = R.layout.ingredient_item

}