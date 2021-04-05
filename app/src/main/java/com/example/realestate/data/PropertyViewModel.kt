package com.example.realestate.data

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PropertyViewModel @Inject constructor(
    repository: PropertyRepository
): ViewModel() {

    val properties = repository.getProperties().asLiveData()
}