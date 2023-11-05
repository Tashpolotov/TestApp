package com.example.testapp.domain.repository

import com.example.testapp.domain.model.*
import retrofit2.Call
import retrofit2.Response


interface FoodRepository {

    suspend fun getFood(): List<Category>

    suspend fun getMealsByCategory(): List<Category>

    suspend fun getRecommendation():List<Recommendation>

}