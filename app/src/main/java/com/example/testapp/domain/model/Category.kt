package com.example.testapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String,
    @PrimaryKey(autoGenerate = true)
    var id:Int,
)
