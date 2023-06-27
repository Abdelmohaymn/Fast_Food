package com.example.fastfood.viewModel.repos

import com.example.fastfood.domain.mapper.FromFavRecipeMapper
import com.example.fastfood.domain.mapper.ToFavRecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.RecipeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesRepositry(private val recipeDao: RecipeDao) {

    suspend fun getFavs():List<MyRecipe>{
        return withContext(Dispatchers.IO){
            recipeDao.getFavRecipes().map {
                it.let { FromFavRecipeMapper().map(it) }
            }
        }
    }

    suspend fun insertFavRecipe(favRecipe: MyRecipe){
        recipeDao.insertRecipe(ToFavRecipeMapper().map(favRecipe))
    }

    suspend fun deleteFavRecipe(favRecipe: MyRecipe){
        recipeDao.deleteRecipe(ToFavRecipeMapper().map(favRecipe))
    }

}