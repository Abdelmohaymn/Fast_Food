package com.example.fastfood.viewModel.viewsm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.roomDb.RecipeDatabase
import com.example.fastfood.viewModel.repos.FavoritesRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoritesRepositry
):ViewModel() {

    val favs = MutableLiveData<List<MyRecipe>>()

    fun getAllFavs(){
        viewModelScope.launch {
            favs.postValue(repository.getFavs())
        }
    }

   /* fun insertFavRecipe(favRecipe: MyRecipe) = viewModelScope.launch(Dispatchers.IO){
        repository.insertFavRecipe(favRecipe)
    }
*/
    fun deleteFavRecipe(favRecipe: MyRecipe) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteFavRecipe(favRecipe)
    }

}