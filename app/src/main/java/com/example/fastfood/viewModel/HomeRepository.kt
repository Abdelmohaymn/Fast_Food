package com.example.fastfood.viewModel


import com.example.fastfood.State
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.RecipeList
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.flowWithMaping
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class HomeRepository {

    private val api = RetrofitInstance.api
    private val recipeMapper = RecipeMapper()
    fun getRandomRecipe() : Flow<State<List<MyRecipe?>?>> = flowWithMaping(recipeMapper) {
        api.getRandomRecipe(1) }
    fun getPopularRecipes() : Flow<State<List<MyRecipe?>?>> = flowWithMaping(recipeMapper) {
        api.getRandomRecipe(100,"popularity") }

    /*fun wrapWithFlow(function : suspend () -> Response<RecipeList>) : Flow<State<List<MyRecipe?>?>>{
        return flow {
            emit(State.Loading)
            try {
                val response = function().body()?.recipes?.map {
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