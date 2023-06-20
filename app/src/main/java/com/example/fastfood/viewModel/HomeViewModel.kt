package com.example.fastfood.viewModel

import androidx.lifecycle.*
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.RecipeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel:ViewModel() {

    private val repository = HomeRepository()
    val randomRecipee = MutableLiveData<State<List<MyRecipe?>?>>()
    val popularRecipes = repository.getPopularRecipes().asLiveData(Dispatchers.IO)

    fun getRandomRecipe(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getRandomRecipe().collect{state->
                randomRecipee.postValue(state)
            }
        }
    }

}