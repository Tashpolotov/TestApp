package com.example.testapp.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.Room.FoodDao
import com.example.testapp.domain.model.Category
import com.example.testapp.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(private val repository: FoodRepository, private val foodDao: FoodDao):ViewModel() {


    private val _food = MutableStateFlow(FoodState())
    val food: StateFlow<FoodState> = _food

    fun insertCategory(category: Category) {
        viewModelScope.launch {
            try {
                foodDao.insertCategory(category)
                Log.d("aliana", "Category inserted: $category")
            } catch (e: Exception) {
                Log.e("aliana", "Error inserting category: ${e.message}")
            }
        }
    }

    suspend fun getDataFromDatabase(): List<Category> {
        return try {
            val dataFromDatabase = foodDao.getAllCategories()
            _food.value = _food.value.copy(getAllCategories = dataFromDatabase)

            Log.d("FoodViewModel", "Received data from the database: $dataFromDatabase")

            dataFromDatabase // Return the list
        } catch (e: Exception) {
            Log.e("FoodViewModel", "Error while getting data from the database: ${e.message}")
            emptyList() // Return an empty list or handle the error as needed
        }
    }


    fun getMeals() {
        viewModelScope.launch {
            try {
                val meals = repository.getMealsByCategory()
                _food.value = _food.value.copy(meals = meals)
                Log.d("olo", "Received meals: $meals") // Добавьте эту строку для логирования
            } catch (e: Exception) {
                Log.e("olo", "Error getting meals: ${e.message}", e) // Логирование ошибки
            }
        }
    }

    fun getFood(){
        viewModelScope.launch {
            try {
                Log.d("FoodViewModel", "Calling getFood in FoodViewModel")
                val food = repository.getFood()

                // Логируем полученные данные
                Log.d("FoodViewModel", "Received food: $food")

                _food.value = _food.value.copy(food = food)
            } catch (e: Exception) {
                // Обработка ошибки, если что-то пошло не так
                // Здесь можно также добавить логирование ошибки
                Log.e("FoodViewModel", "Error while getting food: ${e.message}")
            }
        }
    }

    fun getRecommendation(){
        viewModelScope.launch {
            val recommendation = repository.getRecommendation()
            _food.value = _food.value.copy(recommendation = recommendation)
        }
    }
}