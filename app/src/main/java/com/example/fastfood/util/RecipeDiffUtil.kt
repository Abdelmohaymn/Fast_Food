package com.example.fastfood.util

import androidx.recyclerview.widget.DiffUtil
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.autoComplete.AutoCompleteResponseItem

class RecipeDiffUtil<T>(private val oldItems:List<T>,private val newItems:List<T>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size
    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       if(oldItems[oldItemPosition] is MyRecipe){
           return (oldItems[oldItemPosition] as MyRecipe).id==(newItems[newItemPosition] as MyRecipe).id
       }else if(oldItems[oldItemPosition] is MyMiniRecipe){
           return (oldItems[oldItemPosition] as MyMiniRecipe).id==(newItems[newItemPosition] as MyMiniRecipe).id
       }else{
           return (oldItems[oldItemPosition] as AutoCompleteResponseItem).id==(newItems[newItemPosition] as AutoCompleteResponseItem).id
       }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if(oldItems[oldItemPosition] is MyRecipe){
            val old = oldItems as List<MyRecipe>
            val new = newItems as List<MyRecipe>
            return when{
                old[oldItemPosition].id != new[newItemPosition].id
                        ||old[oldItemPosition].title != new[newItemPosition].title
                        ||old[oldItemPosition].image != new[newItemPosition].image
                        ||old[oldItemPosition].summary != new[newItemPosition].summary
                ->false
                else -> true
            }
        }else if(oldItems[oldItemPosition] is MyMiniRecipe){
            val old = oldItems as List<MyMiniRecipe>
            val new = newItems as List<MyMiniRecipe>
            return when {
                old[oldItemPosition].id != new[newItemPosition].id
                        || old[oldItemPosition].title != new[newItemPosition].title
                        || old[oldItemPosition].image != new[newItemPosition].image
                -> false
                else -> true
            }
        }else{
            val old = oldItems as List<AutoCompleteResponseItem>
            val new = newItems as List<AutoCompleteResponseItem>
            return when {
                old[oldItemPosition].id != new[newItemPosition].id
                        || old[oldItemPosition].title != new[newItemPosition].title
                -> false
                else -> true
            }
        }
    }
}