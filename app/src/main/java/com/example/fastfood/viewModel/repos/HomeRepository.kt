package com.example.fastfood.viewModel.repos


import android.util.Log
import com.example.fastfood.State
import com.example.fastfood.domain.mapper.FromStoredRecipeMapper
import com.example.fastfood.domain.mapper.ToStoredRecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.network.RecipeApi
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.roomDb.RecipeDao
import com.example.fastfood.roomDb.StoredRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val recipeDao:RecipeDao,
    private val api: RecipeApi,
    private val fromStoredRecipeMapper:FromStoredRecipeMapper,
    private val toStoredRecipeMapper:ToStoredRecipeMapper
) {

    /*un getRandomRecipe() : Flow<State<List<MyRecipe?>?>> = flowWithMaping(recipeMapper) {
        api.getRandomRecipe(1) }*/
    /*fun getPopularRecipes() : Flow<State<List<MyRecipe?>?>> = flowWithMaping(recipeMapper) {
        api.getRandomRecipe(100,"popularity") }*/

    suspend fun getRandomRecipe(): Flow<State<MyRecipe>>{
        return flow {
            emit(State.Loading)
            val res = withContext(Dispatchers.IO){
                recipeDao.getRandomRecipe()
            }
            if (res!=null){
                emit(State.Success(fromStoredRecipeMapper.map(res)))
            }else{
                emit(State.Error(""))
            }
        }
    }


    suspend fun getPopularRecipes():List<MyRecipe>{
        return withContext(Dispatchers.IO) {
            recipeDao.getAllRecipes().map {
                it.let { fromStoredRecipeMapper.map(it) }
            }
        }
    }

    suspend fun refreshRecipes(){
        try{
            val response = api.getRandomRecipe(100,"popularity")
            val recipes = response.body()?.recipes?.map {
                it?.let { it1 -> toStoredRecipeMapper.map(it1) }
            } as List<StoredRecipe>
            if(recipes!=null){
                withContext(Dispatchers.IO){
                    recipeDao.deleteAllRecords()
                    recipeDao.addRecipes(recipes)
                }
            }
        }catch (e:Exception){
            Log.d("homeRepo",e.message.toString())
        }
    }

    suspend fun refreshRandomRedcipe(){
        try{
            val response = api.getRandomRecipe(1)
            val recipes = response.body()?.recipes?.map {
                it?.let { it1 -> toStoredRecipeMapper.map(it1) }
            } as List<StoredRecipe>
            if(recipes!=null){
                withContext(Dispatchers.IO){
                    recipeDao.addRecipes(recipes)
                }
            }
        }catch (e:Exception){
            Log.d("homeRepo",e.message.toString())
        }
    }

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