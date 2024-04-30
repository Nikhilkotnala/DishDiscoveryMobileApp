package uk.ac.tees.mad.D3939804.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.tees.mad.D3939804.dao.RecipeDao
import uk.ac.tees.mad.D3939804.entities.*
import uk.ac.tees.mad.D3939804.entities.converter.CategoryListConverter
import uk.ac.tees.mad.D3939804.entities.converter.MealListConverter

@Database(entities = [Recipes::class,CategoryItems::class,Category::class,Meal::class,MealsItems::class,Favourites::class],
    version = 2,exportSchema = true, autoMigrations = [AutoMigration(from = 1, to = 2)])
@TypeConverters(CategoryListConverter::class,MealListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                        context,
                        RecipeDatabase::class.java,
                        "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao():RecipeDao
}