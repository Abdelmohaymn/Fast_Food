package com.example.fastfood.roomDb

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.ExtendedIngredient
import java.util.Date

@Entity(tableName = "fav_recipes")
data class FavRecipe(
    @PrimaryKey
    val id: Int=0,
    val date: Date,
    @Embedded
    val recipe:RecipeEntity
)
