package com.example.fastfood.viewModel.viewsm

import android.content.Context
import androidx.lifecycle.*
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.RecipeDatabase
import com.example.fastfood.viewModel.repos.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
):ViewModel() {

    //private val repository = HomeRepository(RecipeDatabase.getDatabase(context).getRecipeDao())
    val randomRecipee = MutableLiveData<State<MyRecipe>>()
    val popularRecipes = MutableLiveData<List<MyRecipe>>()

    init {
        viewModelScope.launch {
            repository.refreshRecipes()
            popularRecipes.postValue(repository.getPopularRecipes())
        }
    }

    fun getRandomRecipe(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshRandomRedcipe()
            repository.getRandomRecipe().collect{state->
                randomRecipee.postValue(state)
            }
        }
    }

}