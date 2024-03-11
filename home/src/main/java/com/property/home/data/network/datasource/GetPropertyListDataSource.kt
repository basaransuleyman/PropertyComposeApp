package com.property.home.data.network.datasource

import com.property.home.data.network.model.PropertyListResponse

interface GetPropertyListDataSource {
    suspend fun getPropertyList(): PropertyListResponse
}