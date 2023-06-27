package com.example.fastfood.viewModel.viewsm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.RecipeDatabase
import com.example.fastfood.viewModel.repos.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecipeViewModel(context: Context) : ViewModel() {
    private var repository = RecipeRepository(RecipeDatabase.getDatabase(context).getRecipeDao())
    val similarRecipes = MutableLiveData<State<List<MyMiniRecipe?>?>>()
    val recipeInfo = MutableLiveData<State<MyRecipe?>>()

    fun getSimilarRecipes(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSimilarRecipes(id).collect{ state->
                similarRecipes.postValue(state)
            }
        }
    }
    fun getRecipeInfo(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRecipeInfo(id).collect{state->
                recipeInfo.postValue(state)
            }
        }
    }
////////////////Room//////
    fun insertFavRecipe(favRecipe: MyRecipe) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFavRecipe(favRecipe)
    }

    fun deleteFavRecipe(favRecipe: MyRecipe) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFavRecipe(favRecipe)
    }

    fun getRecipeById(id: Int):MyRecipe?{
        var recipe:MyRecipe? = null
        runBlocking{
            recipe = repository.getRecipeById(id)
        }
        return recipe
    }

}