package com.example.restaurantapp.retrofit

import com.example.restaurantapp.model.CategoryList
import com.example.restaurantapp.model.MealsByCategoryList
import com.example.restaurantapp.model.MealList
import com.example.restaurantapp.model.MealsByCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealList>

    @GET("filter.php?")
    fun getPopularItens(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("search.php")
    fun searchMeals(@Query("s") searchQuery: String): Call<MealList>
}