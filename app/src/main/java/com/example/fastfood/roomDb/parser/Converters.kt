package com.example.fastfood.roomDb.parser

import androidx.room.TypeConverter
import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {
    private val gsonParser = GsonParser()

    @TypeConverter
    fun fromAnalyzedInstruction(list:List<AnalyzedInstruction?>?):String{
        val type = object : TypeToken<List<AnalyzedInstruction>>(){}.type
        return gsonParser.toJson(list,type)?:"[]"
        //return Gson().toJson(list,type)
    }
    @TypeConverter
    fun toAnalyzedInstruction(json:String):List<AnalyzedInstruction?>?{
        val type = object : TypeToken<List<AnalyzedInstruction>>(){}.type
        return gsonParser.fromJson<List<AnalyzedInstruction>>(json,type)?: emptyList()
        //return Gson().fromJson(json,type)
    }

    @TypeConverter
    fun fromExtendedIngredient(list:List<ExtendedIngredient?>?):String{
        val type = object : TypeToken<List<ExtendedIngredient>>(){}.type
        return gsonParser.toJson(list,type)?:"[]"
        //return Gson().toJson(list,type)
    }
    @TypeConverter
    fun toExtendedIngredient(json:String):List<ExtendedIngredient?>?{
        val type = object : TypeToken<List<ExtendedIngredient>>(){}.type
        return gsonParser.fromJson<List<ExtendedIngredient>>(json,type)?: emptyList()
        //return Gson().fromJson(json,type)
    }

    @TypeConverter
    fun fromString(list:List<String?>?):String{
        val type = object : TypeToken<List<String>>(){}.type
        return gsonParser.toJson(list,type)?:"[]"
        //return Gson().toJson(list,type)
    }
    @TypeConverter
    fun toString(json:String):List<String?>?{
        val type = object : TypeToken<List<String>>(){}.type
        return gsonParser.fromJson<List<String>>(json,type)?: emptyList()
        //return Gson().fromJson(json,type)
    }

    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}