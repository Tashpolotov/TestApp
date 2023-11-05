package com.example.testapp.domain.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.domain.model.Category

@Database(version = 1, entities = [Category::class])
abstract class AppDataBase:RoomDatabase() {

    abstract fun getFood():FoodDao

}