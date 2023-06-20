package com.example.fastfood.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: FavRecipe)

    @Delete
    suspend fun deleteRecipe(recipe: FavRecipe)

    @Query("select * from fav_recipes order by date DESC")
    suspend fun getFavRecipes():List<FavRecipe>

    @Query("SELECT * FROM fav_recipes WHERE id=:recipeId")
    suspend fun getRecipeById(recipeId: Int): FavRecipe?
}