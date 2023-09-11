package com.example.fastfood.di

import com.example.fastfood.domain.mapper.FromFavRecipeMapper
import com.example.fastfood.domain.mapper.FromSearchRecipeToMyMini
import com.example.fastfood.domain.mapper.FromSimilarItemToMyMini
import com.example.fastfood.domain.mapper.FromStoredRecipeMapper
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.mapper.ToFavRecipeMapper
import com.example.fastfood.domain.mapper.ToStoredRecipeMapper
import com.example.fastfood.network.RecipeApi
import com.example.fastfood.roomDb.RecipeDao
import com.example.fastfood.viewModel.repos.CategoryRecipeRepository
import com.example.fastfood.viewModel.repos.FavoritesRepositry
import com.example.fastfood.viewModel.repos.HomeRepository
import com.example.fastfood.viewModel.repos.RecipeRepository
import com.example.fastfood.viewModel.repos.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestModule {
    @Provides
    fun provideHomeRepo(
        recipeDao: RecipeDao,
        api: RecipeApi,
        fromStoredRecipeMapper:FromStoredRecipeMapper,
        toStoredRecipeMapper:ToStoredRecipeMapper
    ):HomeRepository{
        return HomeRepository(recipeDao,api,fromStoredRecipeMapper,toStoredRecipeMapper)
    }

    @Provides
    fun provideFavRepo(
        recipeDao: RecipeDao,
        fromFavRecipeMapper:FromFavRecipeMapper,
        toFavRecipeMapper:ToFavRecipeMapper
    ):FavoritesRepositry{
        return FavoritesRepositry(recipeDao,fromFavRecipeMapper,toFavRecipeMapper)
    }

    @Provides
    fun provideSearchRepo(
        api: RecipeApi,
        fromSearchRecipeToMyMini:FromSearchRecipeToMyMini
    ):SearchRepository{
        return SearchRepository(api,fromSearchRecipeToMyMini)
    }

    @Provides
    fun provideRecipeRepo(
        recipeDao: RecipeDao,
        api: RecipeApi,
        recipeMapper:RecipeMapper,
        toFavRecipeMapper:ToFavRecipeMapper,
        fromFavRecipeMapper:FromFavRecipeMapper,
        fromSimilarItemToMyMini:FromSimilarItemToMyMini
    ):RecipeRepository{
        return RecipeRepository(
            recipeDao,
            api,
            recipeMapper,
            toFavRecipeMapper,
            fromFavRecipeMapper,
            fromSimilarItemToMyMini
        )
    }

    @Provides
    fun provideCategoryRepo(
        api: RecipeApi,
        recipeMapper:RecipeMapper
    ):CategoryRecipeRepository{
        return CategoryRecipeRepository(api,recipeMapper)
    }

    @Provides
    fun provideRecipeMapper():RecipeMapper = RecipeMapper()

    @Provides
    fun provideFromFavRecipeMapper():FromFavRecipeMapper = FromFavRecipeMapper()
    @Provides
    fun provideToFavRecipeMapper():ToFavRecipeMapper = ToFavRecipeMapper()

    @Provides
    fun provideFromStoredRecipeMapper():FromStoredRecipeMapper = FromStoredRecipeMapper()
    @Provides
    fun provideToStoredRecipeMapper(): ToStoredRecipeMapper = ToStoredRecipeMapper()
    @Provides
    fun provideFromSimilarItemToMyMini(): FromSimilarItemToMyMini = FromSimilarItemToMyMini()
    @Provides
    fun provideFromSearchRecipeToMyMini(): FromSearchRecipeToMyMini = FromSearchRecipeToMyMini()

}