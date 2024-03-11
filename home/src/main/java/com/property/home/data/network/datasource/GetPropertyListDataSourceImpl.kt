package com.property.home.data.network.datasource

import com.property.core.network.handleCall
import com.property.home.data.network.HomeApi
import com.property.home.data.network.model.PropertyListResponse
import javax.inject.Inject

internal class GetPropertyListDataSourceImpl @Inject constructor(
    private val api: HomeApi
) : GetPropertyListDataSource {
    override suspend fun getPropertyList(): PropertyListResponse {
        return handleCall {
            api.getPropertyList()
        }
    }
}