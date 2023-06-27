package com.example.fastfood.viewModel.viewsm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fastfood.State
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.model.autoComplete.AutoCompleteResponse
import com.example.fastfood.model.complexSearch.ComplexSearchResponse
import com.example.fastfood.viewModel.repos.SearchRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel:ViewModel() {
    private val repository = SearchRepository()
    val suggestions = MutableLiveData<State<AutoCompleteResponse>>()
    val recipes = MutableLiveData<State<List<MyMiniRecipe?>?>>()

    fun getSeggestions(query: String){
        viewModelScope.launch {
            repository.getSuggestions(query).collect{state ->
                suggestions.postValue(state)
            }
        }
    }

    fun getRecipesFromSearch(query: String){
        if(!query.isNullOrBlank()){
            viewModelScope.launch {
                repository.getRecipesFromSearch(query).collect{state ->
                    recipes.postValue(state)
                }
            }
        }
    }

}