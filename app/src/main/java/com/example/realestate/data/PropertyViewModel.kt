package com.example.realestate.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestate.api.PropertyApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    api: PropertyApiService
): ViewModel() {

    private val propertyLiveData = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> = propertyLiveData

    init {
        viewModelScope.launch {
            val properties = api.getProperties()
            Log.i("zip",api.getProperties().get(0).zip)
            propertyLiveData.value = properties

        }

    }
}