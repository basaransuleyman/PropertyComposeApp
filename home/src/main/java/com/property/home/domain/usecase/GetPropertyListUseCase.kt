package com.property.home.domain.usecase

import com.property.core.model.Property
import kotlinx.coroutines.flow.Flow

interface GetPropertyListUseCase {
    fun getPropertyList(): Flow<List<Property>>
}