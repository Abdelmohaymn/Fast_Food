package com.example.fastfood.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

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

    //////////////

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipes(recipes:List<StoredRecipe>)

    @Query("select * from stored_recipes")
    suspend fun getAllRecipes(): List<StoredRecipe>

    @Query("DELETE FROM stored_recipes")
    suspend fun deleteAllRecords()

    @Query("SELECT * FROM stored_recipes ORDER BY timestamp DESC LIMIT 1")
    suspend fun getRandomRecipe():StoredRecipe
}