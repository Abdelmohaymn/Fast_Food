package com.example.fastfood.viewModel.viewsm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.viewModel.repos.CategoryRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRecipeViewModel(type:String) : ViewModel() {
    private val repository = CategoryRecipeRepository()
    val recipes = MutableLiveData<State<List<MyRecipe?>?>>()

    init {
        getRecipes(type)
    }

    fun getRecipes(type:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategoryRecipes(type).collect{ state ->
                recipes.postValue(state)
            }
        }
    }

}