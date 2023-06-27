package com.example.fastfood.roomDb

import android.content.Context
import androidx.room.*
import com.example.fastfood.roomDb.parser.Converters
import com.example.fastfood.util.DATABASE_NAME

@Database(entities = [FavRecipe::class,StoredRecipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase:RoomDatabase() {
    abstract fun getRecipeDao():RecipeDao

    companion object{
        @Volatile private var INSTANCE:RecipeDatabase? = null
        fun getDatabase(context: Context):RecipeDatabase{
            return INSTANCE?:synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}