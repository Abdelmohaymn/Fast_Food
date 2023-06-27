package com.example.fastfood.roomDb

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stored_recipes")
data class StoredRecipe(
    @PrimaryKey
    val id: Int=0,
    val timestamp:Long,
    @Embedded
    val recipe:RecipeEntity
)