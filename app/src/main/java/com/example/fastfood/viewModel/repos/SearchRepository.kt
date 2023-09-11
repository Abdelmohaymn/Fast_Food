package com.example.fastfood.viewModel.repos

import com.example.fastfood.State
import com.example.fastfood.domain.mapper.FromSearchRecipeToMyMini
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.network.RecipeApi
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.flowWithMaping
import com.example.fastfood.util.wrapWithFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api:RecipeApi,
    private val fromSearchRecipeToMyMini:FromSearchRecipeToMyMini
    ) {

    fun getSuggestions(query:String) = wrapWithFlow { api.getAutoCompleteSearch(query) }

    fun getRecipesFromSearch(query:String): Flow<State<List<MyMiniRecipe?>?>> = flowWithMaping(fromSearchRecipeToMyMini) { api.getRecipesFromSearch(query) }
}