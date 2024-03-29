package com.example.fastfood.viewModel.repos

import com.example.fastfood.State
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.network.RecipeApi
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.flowWithMaping
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRecipeRepository @Inject constructor(
    private val api:RecipeApi,
    private val recipeMapper:RecipeMapper
){
    //private val recipeMapper = RecipeMapper()

    fun getCategoryRecipes(type:String) : Flow<State<List<MyRecipe?>?>> = flowWithMaping(recipeMapper) {
        api.getRandomRecipe(100, "popularity", type)
    }
    /*fun getCategoryRecipes(type:String) : Flow<State<List<MyRecipe?>?>>{
        return flow {
            emit(State.Loading)
            try {
                val response = api.getRandomRecipe(100,"popularity",type).body()?.recipes?.map {
                        recipeDto ->
                    recipeDto?.let { recipeMapper.map(it) }
                }
                emit(State.Success(response))
            }catch (e:Exception){
                emit(State.Error(e.message.toString()))
            }
        }
    }*/
}