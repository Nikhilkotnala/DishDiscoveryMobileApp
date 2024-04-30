package uk.ac.tees.mad.D3939804.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uk.ac.tees.mad.D3939804.entities.Category
import uk.ac.tees.mad.D3939804.entities.CategoryItems
import uk.ac.tees.mad.D3939804.entities.Favourites
import uk.ac.tees.mad.D3939804.entities.MealsItems

@Dao
interface RecipeDao {

    @Query("SELECT * FROM categoryitems ORDER BY id DESC")
    fun getAllCategory() : List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMeal(mealsItems: MealsItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourites(fav: Favourites?)

    @Query("SELECT * FROM Favourites WHERE email = :email ORDER BY id DESC")
    fun getFavourites(email:String) : List<Favourites>

    @Query("DELETE FROM categoryitems")
    fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    fun getSpecificMealList(categoryName:String) : List<MealsItems>

    @Query("SELECT * FROM MealItems WHERE idMeal IN (:idMeals) ORDER BY id DESC")
    fun getMealListById(idMeals : List<Int>) : List<MealsItems>
}