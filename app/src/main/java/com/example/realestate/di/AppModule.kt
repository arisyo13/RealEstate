package com.example.realestate.di

import android.app.Application
import androidx.room.Room
import com.example.realestate.api.PropertyApiService
import com.example.realestate.data.PropertyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PropertyApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePropertyApi(retrofit: Retrofit): PropertyApiService =
        retrofit.create(PropertyApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): PropertyDatabase =
        Room.databaseBuilder(app, PropertyDatabase::class.java, "property_database")
            .build()
}