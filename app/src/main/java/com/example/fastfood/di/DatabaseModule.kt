package com.example.fastfood.di

import android.content.Context
import com.example.fastfood.FastFoodApplication
import com.example.fastfood.roomDb.RecipeDao
import com.example.fastfood.roomDb.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideRecipeDAO(@ApplicationContext context:Context):RecipeDao{
        return RecipeDatabase.getDatabase(context).getRecipeDao()
    }

}