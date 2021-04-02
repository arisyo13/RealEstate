package com.example.realestate.data

import PropertyDao
import androidx.lifecycle.LiveData


class PropertyRepository(private val propertyDao: PropertyDao) {
    val readAllData: LiveData<List<Property>> = propertyDao.getAllProperties()

    suspend fun insertProperty(property: Property){
        propertyDao.insertProperty(property)
    }
}