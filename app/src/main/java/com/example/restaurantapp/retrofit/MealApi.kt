package com.example.restaurantapp.retrofit

import com.example.restaurantapp.model.MealList
import retrofit2.Call
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<MealList>
}