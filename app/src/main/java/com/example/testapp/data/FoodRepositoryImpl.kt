package com.example.testapp.data

import android.util.Log
import com.example.testapp.R
import com.example.testapp.data.network.FoodApiService
import com.example.testapp.domain.model.*
import com.example.testapp.domain.repository.FoodRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepositoryImpl(private val foodApiService: FoodApiService) : FoodRepository {
    override suspend fun getFood(): List<Category> {
        try {
            val response = foodApiService.getCategories()
            if (response.isSuccessful) {
                val categoryResponse = response.body()
                val categories = categoryResponse?.categories ?: emptyList()
                Log.d("FoodRepositoryImpl", "Received categories: $categories")
                return categories
            } else {

                throw Exception("Failed to get food data")
            }
        } catch (e: Exception) {

            throw e
        }
    }

    override suspend fun getMealsByCategory(): List<Category> {
        val response = foodApiService.getMealsByCategory()
        if (response.isSuccessful) {
            val categoryList = response.body()
            val categories = categoryList?.categories ?: emptyList()
            Log.d("ololo", "Received categories: $categories")
            return categories
        } else {
            Log.e("ololo", "Error getting meals: ${response.message()}")
            throw Exception("Failed to get food data")
        }
    }

    override suspend fun getRecommendation(): List<Recommendation> {
        val recommendations = listOf(
            Recommendation(R.drawable.img_recc1),
            Recommendation(R.drawable.img_recc2),
            Recommendation(R.drawable.img_recc3)
        )
        Log.d("FoodRepositoryImpl", "Received recommendations: $recommendations")
        return recommendations
    }
}