package com.example.fastfood.viewModel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastfood.viewModel.viewsm.CategoryRecipeViewModel

class CategoryRecipeFactory(private val type:String):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryRecipeViewModel(type) as T
    }
}