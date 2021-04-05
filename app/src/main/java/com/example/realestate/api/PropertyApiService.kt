package com.example.realestate.api

import com.example.realestate.data.Property
import retrofit2.http.GET
import retrofit2.http.Headers

interface PropertyApiService {
    companion object{
        const val BASE_URL = "https://intern.docker-dev.d-tt.nl/api/"
    }

    @Headers("Access-Key: 98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    @GET("house")
    suspend fun getProperties(): List<Property>
}