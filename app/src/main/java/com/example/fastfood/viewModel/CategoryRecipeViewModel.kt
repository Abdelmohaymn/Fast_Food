package com.example.fastfood.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.RecipeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRecipeViewModel : ViewModel() {
    private val repository = CategoryRecipeRepository()
    val recipes = MutableLiveData<State<List<MyRecipe?>?>>()

    fun getRecipes(type:String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategoryRecipes(type).collect{ state ->
                recipes.postValue(state)
            }
        }
    }

}