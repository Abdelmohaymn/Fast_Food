package com.example.fastfood.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.RecipeDatabase
import kotlinx.coroutines.launch

class FavoritesViewModel(context: Context):ViewModel() {
    private val repositry = FavoritesRepositry(RecipeDatabase.getDatabase(context).getRecipeDao())
    val favs = MutableLiveData<List<MyRecipe>>()

    fun getAllFavs(){
        viewModelScope.launch {
            favs.postValue(repositry.getFavs())
        }
    }


}