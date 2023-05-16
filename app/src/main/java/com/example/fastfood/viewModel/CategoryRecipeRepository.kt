package com.example.fastfood.viewModel

import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.wrapWithFlow

class CategoryRecipeRepository {
    val api = RetrofitInstance.api

    fun getCategoryRecipes(type:String) = wrapWithFlow { api.getRandomRecipe(100,"popularity",type) }
}