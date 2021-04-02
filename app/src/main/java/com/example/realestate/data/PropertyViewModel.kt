package com.example.realestate.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PropertyViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<Property>>
    private val repository: PropertyRepository

    init {
        val propertyDao = PropertyDatabase.getDatabase(application).propertyDao()
        repository = PropertyRepository(propertyDao)
        readAllData = repository.readAllData
    }

    fun insertProperty(property: Property){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertProperty(property)
        }
    }
}