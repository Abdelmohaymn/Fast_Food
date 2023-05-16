package com.example.fastfood.viewModel

import androidx.lifecycle.*
import com.example.fastfood.State
import com.example.fastfood.model.recipesList.RecipeList
import kotlinx.coroutines.launch


class HomeViewModel:ViewModel() {

    private val repository = HomeRepository()
    val randomRecipee = MutableLiveData<State<RecipeList>>()
    val popularRecipes = repository.getPopularRecipes().asLiveData()

    fun getRandomRecipe(){
        viewModelScope.launch {
            repository.getRandomRecipe().collect{state->
                randomRecipee.postValue(state)
            }
        }
    }

}