package uk.ac.tees.mad.D3939804.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.mad.D3939804.entities.Category
import uk.ac.tees.mad.D3939804.entities.CategoryItems
import uk.ac.tees.mad.D3939804.entities.MealsItems

@Dao
interface RecipeDao {

    @Query("SELECT * FROM categoryitems ORDER BY id DESC")
    fun getAllCategory() : List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(mealsItems: MealsItems?)

    @Query("DELETE FROM categoryitems")
    fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    fun getSpecificMealList(categoryName:String) : List<MealsItems>
}