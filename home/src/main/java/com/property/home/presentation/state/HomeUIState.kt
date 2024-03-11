package com.property.home.presentation.state

import com.property.core.model.Property
import javax.annotation.concurrent.Immutable

@Immutable
data class HomeUIState(
    val isLoading: Boolean = false,
    val propertyList: List<Property>? = null,
    val error: Throwable? = null
)