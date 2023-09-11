package com.example.fastfood.viewModel.repos

import com.example.fastfood.State
import com.example.fastfood.domain.mapper.FromFavRecipeMapper
import com.example.fastfood.domain.mapper.FromSimilarItemToMyMini
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.mapper.ToFavRecipeMapper
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.similarRecipes.SimilarRecipes
import com.example.fastfood.network.RecipeApi
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.roomDb.RecipeDao
import com.example.fastfood.util.flowWithMaping
import com.example.fastfood.util.wrapWithFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeRepository @Inject constructor(
        private val recipeDao: RecipeDao,
        private val api:RecipeApi,
        private val recipeMapper:RecipeMapper,
        private val toFavRecipeMapper:ToFavRecipeMapper,
        private val fromFavRecipeMapper:FromFavRecipeMapper,
        private val fromSimilarItemToMyMini:FromSimilarItemToMyMini
    ) {

    fun getSimilarRecipes(id:Int):Flow<State<List<MyMiniRecipe?>?>> = flowWithMaping(fromSimilarItemToMyMini){ api.getSimilarRecipes(id,10,"random") }
    fun getRecipeInfo(id:Int) : Flow<State<MyRecipe?>> = flowWithMaping(recipeMapper){api.getRecipeInfo(id)}

    //////////room/////////
    suspend fun insertFavRecipe(favRecipe: MyRecipe){
        recipeDao.insertRecipe(toFavRecipeMapper.map(favRecipe))
    }

    suspend fun deleteFavRecipe(favRecipe: MyRecipe){
        recipeDao.deleteRecipe(toFavRecipeMapper.map(favRecipe))
    }

    suspend fun getRecipeById(id:Int):MyRecipe?{
        return withContext(Dispatchers.IO){
            recipeDao.getRecipeById(id)?.let { fromFavRecipeMapper.map(it) }
        }
    }


}