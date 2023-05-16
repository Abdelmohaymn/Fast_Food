package com.example.fastfood.network

import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.model.recipesList.RecipeList
import com.example.fastfood.model.similarRecipes.SimilarRecipes
import com.example.fastfood.util.SPOON_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/random")
    suspend fun getRandomRecipe(
        @Query("number") number:Int,
        @Query("sort") sort:String="",
        @Query("type") type:String="",
        @Query("apiKey") apiKey:String = SPOON_API_KEY
    ):Response<RecipeList>

    @GET("recipes/{id}/similar")
    suspend fun getSimilarRecipes(
        @Path("id") id:Int,
        @Query("number") number:Int,
        @Query("sort") sort:String="",
        @Query("apiKey") apiKey:String = SPOON_API_KEY,
    ):Response<SimilarRecipes>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInfo(
        @Path("id") id:Int,
        @Query("apiKey") apiKey:String = SPOON_API_KEY,
    ):Response<Recipe>
}