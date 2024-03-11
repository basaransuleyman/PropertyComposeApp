package com.property.home.data.network

import com.property.home.data.network.model.PropertyListResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("/ofarukcelik/ofarukcelik/master/android-test-case.json")
    suspend fun getPropertyList(): Response<PropertyListResponse>

}