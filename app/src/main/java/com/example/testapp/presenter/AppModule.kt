package com.example.testapp.presenter

import android.app.Application
import androidx.room.Room
import com.example.testapp.data.FoodRepositoryImpl
import com.example.testapp.data.network.FoodApiService
import com.example.testapp.domain.Room.AppDataBase
import com.example.testapp.domain.Room.FoodDao
import com.example.testapp.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : FoodApiService{
        return retrofit.create(FoodApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: FoodApiService):FoodRepository{
        return FoodRepositoryImpl(apiService)
    }

    @Provides
    fun provideFoodDao(appDatabase: AppDataBase): FoodDao {
        return appDatabase.getFood()
    }
    @Provides
    fun provideAppDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "food").build()
    }

}