package com.example.fastfood.adapters

import com.example.fastfood.BR
import com.example.fastfood.R

class InstructionsAdapter(items:List<String>):BaseAdapter<String>(items,null) {
    override val layoutId = R.layout.instruction_item

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as ItemViewHolder).binding.setVariable(BR.id,position+1)
    }

}