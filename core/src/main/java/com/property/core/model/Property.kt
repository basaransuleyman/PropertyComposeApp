package com.property.core.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Property(
    val id: String,
    val category: String,
    val city: String,
    val label: String?,
    val district: String,
    val neighborhood: String,
    val currency: String,
    val roomCount: Int,
    val bathCount: Int,
    val price: String,
    val grossSquareMeters: Int,
    val netSquareMeters: Int,
    val roomConfiguration: String,
    val buildingAge: String,
    val description: String,
    val createdDate: String,
    val images: List<String>
)