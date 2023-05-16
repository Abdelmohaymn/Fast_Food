package com.example.fastfood.util

import com.example.fastfood.R
import com.example.fastfood.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


const val SPOON_API_KEY = "86a894c3695b486c9024f0fa969a802c"//"fd7648ceaced4e07870585f48bd9a439"

val mealTypes = listOf(
    "main course","bread","marinade","side dish","breakfast","fingerfood",
    "dessert","soup","snack","appetizer","beverage","drink","salad","sauce")

val colors = listOf<Int>(
    R.color.yellow,
    R.color.pink,
    R.color.teal_200,
    R.color.blue,
    R.color.pink2,
    R.color.purple_200,
    R.color.orange,
    R.color.green
)

fun specificColor():Int{
    return colors.randomOrNull()!!
}

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