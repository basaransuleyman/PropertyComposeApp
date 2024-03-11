package com.property.home.data.network.model

import com.google.gson.annotations.SerializedName

data class PropertyListResponse(
    @SerializedName("data")
    val data: List<Property>
)

data class Property(
    @SerializedName("category")
    val category: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("label")
    val label: String?,
    @SerializedName("district")
    val district: String,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("curreny")
    val currency: String,
    @SerializedName("roomCount")
    val roomCount: Int,
    @SerializedName("bathCount")
    val bathCount: Int,
    @SerializedName("price")
    val price: String,
    @SerializedName("gross")
    val gross: Int,
    @SerializedName("net")
    val net: Int,
    @SerializedName("room")
    val room: String,
    @SerializedName("buildingAge")
    val buildingAge: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("createdDate")
    val createdDate: String,
    @SerializedName("images")
    val images: List<String>
)