package com.example.fastfood.viewModel

import androidx.lifecycle.LiveData
import com.example.fastfood.domain.mapper.FromFavRecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.FavRecipe
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

}