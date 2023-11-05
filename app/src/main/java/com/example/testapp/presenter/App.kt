package com.example.testapp.presenter

import android.app.Application
import androidx.room.Room
import com.example.testapp.domain.Room.AppDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

}