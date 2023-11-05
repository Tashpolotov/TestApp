package com.example.testapp.domain.Room

import androidx.room.*
import com.example.testapp.domain.model.Category

@Dao
interface FoodDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertCategory(category: Category)

        @Query("SELECT * FROM food")
        suspend fun getAllCategories(): List<Category>
}