package com.example.realestate.data

import androidx.room.withTransaction
import com.example.realestate.api.PropertyApiService
import com.example.realestate.utils.networkBoundResource
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val api: PropertyApiService,
    private val db: PropertyDatabase
){
    private val propertyDao = db.propertyDao()

    fun getProperties() = networkBoundResource(
        query = {
            propertyDao.getAllProperties()
        },
        fetch = {
            api.getProperties()
        },
        saveFetchResult = { properties ->
            db.withTransaction {
                propertyDao.deleteAllProperties()
                propertyDao.insertProperty(properties)
            }
        }
    )
}