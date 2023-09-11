package com.example.fastfood.di

import com.example.fastfood.network.RecipeApi
import com.example.fastfood.network.RetrofitInstance
import com.example.fastfood.util.BASE_RECIPE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRecipeApi(gsonConverterFactory: GsonConverterFactory):RecipeApi{
        val api:RecipeApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_RECIPE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(RecipeApi::class.java)
        }
        return api
    }

    @Provides
    @Singleton
    fun provideGson():GsonConverterFactory = GsonConverterFactory.create()
}