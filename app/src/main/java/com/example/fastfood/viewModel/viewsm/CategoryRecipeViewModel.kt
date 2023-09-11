package com.example.fastfood.viewModel.viewsm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.di.CategoryViewModelAssistedFactory
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.viewModel.repos.CategoryRecipeRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CategoryRecipeViewModel @AssistedInject constructor(
    private val repository: CategoryRecipeRepository,
    @Assisted private val type:String
) : ViewModel() {

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