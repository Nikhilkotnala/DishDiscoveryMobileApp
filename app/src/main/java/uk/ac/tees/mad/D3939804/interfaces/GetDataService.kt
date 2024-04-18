package uk.ac.tees.mad.D3939804.interfaces

import uk.ac.tees.mad.D3939804.entities.Category
import uk.ac.tees.mad.D3939804.entities.CategoryItems
import uk.ac.tees.mad.D3939804.entities.Meal
import uk.ac.tees.mad.D3939804.entities.MealResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>

    @GET("filter.php")
    fun getMealList(@Query("c") category: String): Call<Meal>

    @GET("lookup.php")
    fun getSpecificItem(@Query("i") id: String): Call<MealResponse>


}