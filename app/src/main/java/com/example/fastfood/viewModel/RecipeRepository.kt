package com.example.fastfood.viewModel

import com.example.fastfood.State
import com.example.fastfood.domain.mapper.FromFavRecipeMapper
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.mapper.ToFavRecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.roomDb.FavRecipe
import com.example.fastfood.roomDb.RecipeDao
import com.example.fastfood.util.flowWithMaping
import com.example.fastfood.util.wrapWithFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RecipeRepository(private val recipeDao: RecipeDao) {

    private val api = RetrofitInstance.api
    private val recipeMapper = RecipeMapper()
    fun getSimilarRecipes(id:Int) = wrapWithFlow { api.getSimilarRecipes(id,10,"random") }
    fun getRecipeInfo(id:Int) : Flow<State<MyRecipe?>> = flowWithMaping(recipeMapper){api.getRecipeInfo(id)}

    suspend fun insertFavRecipe(favRecipe: MyRecipe){
        recipeDao.insertRecipe(ToFavRecipeMapper().map(favRecipe))
    }

    suspend fun deleteFavRecipe(favRecipe: MyRecipe){
        recipeDao.deleteRecipe(ToFavRecipeMapper().map(favRecipe))
    }

    suspend fun getRecipeById(id:Int):MyRecipe?{
        return withContext(Dispatchers.IO){
            recipeDao.getRecipeById(id)?.let { FromFavRecipeMapper().map(it) }
        }
    }


    /*fun getRecipeInfo(id:Int) : Flow<State<MyRecipe?>>{
        return flow {
            emit(State.Loading)
            try {
                val response = api.getRecipeInfo(id).body()?.let { recipeMapper.map(it) }
                emit(State.Success(response))
            }catch (e:Exception){
                emit(State.Error(e.message.toString()))
            }
        }
    }*/

}