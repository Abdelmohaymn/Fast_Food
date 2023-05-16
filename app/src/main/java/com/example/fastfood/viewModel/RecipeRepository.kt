package com.example.fastfood.viewModel

import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.wrapWithFlow

class RecipeRepository {
    val api = RetrofitInstance.api

    fun getSimilarRecipes(id:Int) = wrapWithFlow { api.getSimilarRecipes(id,10,"random") }
    fun getRecipeInfo(id:Int) = wrapWithFlow { api.getRecipeInfo(id) }
}