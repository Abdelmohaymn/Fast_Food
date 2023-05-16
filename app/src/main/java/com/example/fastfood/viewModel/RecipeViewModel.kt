package com.example.fastfood.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.model.similarRecipes.SimilarRecipes
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {
    private val repository = RecipeRepository()
    val similarRecipes = MutableLiveData<State<SimilarRecipes>>()
    val recipeInfo = MutableLiveData<State<Recipe>>()

    fun getSimilarRecipes(id:Int){
        viewModelScope.launch {
            repository.getSimilarRecipes(id).collect{ state->
                similarRecipes.postValue(state)
            }
        }
    }
    fun getRecipeInfo(id:Int){
        viewModelScope.launch {
            repository.getRecipeInfo(id).collect{state->
                recipeInfo.postValue(state)
            }
        }
    }
}