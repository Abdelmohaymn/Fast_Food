package com.example.fastfood.roomDb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.ExtendedIngredient
import java.util.Date

@Entity(tableName = "fav_recipes")
data class FavRecipe(
    val analyzedInstructions: List<AnalyzedInstruction?>? = null,
    val cuisines: List<String?>? = null,
    val dishTypes: List<String?>? = null,
    val extendedIngredients: List<ExtendedIngredient?>? = null,
    val image: String? = null,
    val instructions: String? = null,
    val summary: String? = null,
    val title: String? = null,
    @PrimaryKey
    val id: Int=0,
    val date: Date
)
