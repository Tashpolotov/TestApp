package com.example.testapp.data.network

import com.example.testapp.domain.model.CategoryList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {

    
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryList>

    @GET("categories.php")
    suspend fun getMealsByCategory(): Response<CategoryList>


}