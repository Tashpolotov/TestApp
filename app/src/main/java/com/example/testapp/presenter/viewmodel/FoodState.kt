    package com.example.testapp.presenter.viewmodel

    import com.example.testapp.domain.model.*
    import retrofit2.Call


    data class FoodState(
        val food: List<Category> = emptyList(),
        val meals:List<Category> = emptyList(),
        val recommendation : List<Recommendation> = emptyList(),
        val getAllCategories: List<Category> = emptyList(),
            )