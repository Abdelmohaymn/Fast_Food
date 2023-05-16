package com.example.fastfood.viewModel


import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.wrapWithFlow

class HomeRepository {

    private val api = RetrofitInstance.api

    fun getRandomRecipe() = wrapWithFlow { api.getRandomRecipe(1) }
    fun getPopularRecipes() = wrapWithFlow { api.getRandomRecipe(100,"popularity") }


}