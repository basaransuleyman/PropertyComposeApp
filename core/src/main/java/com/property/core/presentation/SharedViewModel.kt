package com.property.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.property.core.model.Property

class SharedViewModel : ViewModel() {

    var property: Property? by mutableStateOf(null)
        private set

    fun addProperty(newProperty: Property) {
        property = newProperty
    }
}