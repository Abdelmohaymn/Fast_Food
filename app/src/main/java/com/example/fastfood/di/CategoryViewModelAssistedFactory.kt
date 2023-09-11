package com.example.fastfood.di

import com.example.fastfood.viewModel.viewsm.CategoryRecipeViewModel
import dagger.assisted.AssistedFactory

@AssistedFactory
interface CategoryViewModelAssistedFactory {
    fun create(type:String):CategoryRecipeViewModel
}