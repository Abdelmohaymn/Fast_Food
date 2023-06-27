package com.example.fastfood.viewModel.repos

import com.example.fastfood.State
import com.example.fastfood.domain.mapper.FromSearchRecipeToMyMini
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.flowWithMaping
import com.example.fastfood.util.wrapWithFlow
import kotlinx.coroutines.flow.Flow

class SearchRepository {
    val api = RetrofitInstance.api

    fun getSuggestions(query:String) = wrapWithFlow { api.getAutoCompleteSearch(query) }

    fun getRecipesFromSearch(query:String): Flow<State<List<MyMiniRecipe?>?>> = flowWithMaping(FromSearchRecipeToMyMini()) { api.getRecipesFromSearch(query) }
}