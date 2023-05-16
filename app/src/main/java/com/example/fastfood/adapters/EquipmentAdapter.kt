package com.example.fastfood.adapters

import com.example.fastfood.R
import com.example.fastfood.model.recipesList.Equipment

class EquipmentAdapter(items:List<Equipment>):BaseAdapter<Equipment>(items,null) {

    override val layoutId = R.layout.equipment_item
}