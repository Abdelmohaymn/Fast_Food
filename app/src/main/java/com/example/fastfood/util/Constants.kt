package com.example.fastfood.util

import com.example.fastfood.R
import com.example.fastfood.State
import com.example.fastfood.domain.mapper.BaseMapper
import com.example.fastfood.domain.mapper.RecipeMapper
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.complexSearch.ComplexSearchResponse
import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.model.recipesList.RecipeList
import com.example.fastfood.model.similarRecipes.SimilarRecipes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


const val SPOON_API_KEY = "86a894c3695b486c9024f0fa969a802c"//"fd7648ceaced4e07870585f48bd9a439"
const val DATABASE_NAME = "recipe_database"

val mealTypes = listOf(
    Pair("main course",R.drawable.main_course),
    Pair("bread",R.drawable.bread),
    Pair("marinade",R.drawable.marinade),
    Pair("side dish",R.drawable.side_dish),
    Pair("breakfast",R.drawable.breakfast),
    Pair("fingerfood",R.drawable.fingerfood),
    Pair("dessert",R.drawable.dessert),
    Pair("soup",R.drawable.soup),
    Pair("snack",R.drawable.snack),
    Pair("appetizer",R.drawable.appetizer),
    Pair("beverage",R.drawable.beverage),
    Pair("drink",R.drawable.drink),
    Pair("salad",R.drawable.salad),
    Pair("sauce",R.drawable.sauce)
)


fun<T> wrapWithFlow(function:suspend ()-> Response<T>): Flow<State<T>> {
    return flow {
        emit(State.Loading)
        try {
            val res = function()
            if (res.isSuccessful){
                emit(State.Success(res.body()))
            }else{
                emit(State.Error(res.message()))
            }
        }catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }
}


fun<I,O,T,R> flowWithMaping(mapper: BaseMapper<I,O>,function: suspend () -> Response<T>) : Flow<State<R?>>{
    return flow {
        emit(State.Loading)
        try {
            val response = function().body()
            var data:R? = null
            when(response){
                is RecipeList -> {
                    data = response.recipes?.map { dto->
                        dto?.let { mapper.map(it as I) }
                    } as R
                }
                is Recipe -> {
                    data = response.let { mapper.map(it as I) } as R
                }

                is SimilarRecipes -> {
                    data = response.map { dto->
                        dto?.let{mapper.map(it as I)}
                    } as R
                }

                is ComplexSearchResponse -> {
                    data = response.results?.map { dto->
                        dto?.let{mapper.map(it as I)}
                    }as R
                }
            }
            emit(State.Success(data))
        }catch (e:Exception){
            emit(State.Error(e.message.toString()))
        }
    }
}
