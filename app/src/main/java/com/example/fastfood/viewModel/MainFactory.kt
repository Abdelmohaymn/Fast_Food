package com.example.fastfood.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastfood.di.CategoryViewModelAssistedFactory
import com.example.fastfood.viewModel.viewsm.CategoryRecipeViewModel

class MainFactory(
    private val assistedFactory: CategoryViewModelAssistedFactory,
    private val type:String,
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryRecipeViewModel::class.java)){
            return assistedFactory.create(type) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }
}