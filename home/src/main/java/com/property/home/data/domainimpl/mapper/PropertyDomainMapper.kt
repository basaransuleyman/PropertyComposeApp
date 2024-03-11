package com.property.home.data.domainimpl.mapper

import com.property.home.data.network.model.PropertyListResponse
import com.property.core.model.Property

fun PropertyListResponse.toDomainModel(): List<Property> {
    return this.data.map { property ->
        Property(
            id = property.price + property.currency,
            category = property.category,
            city = property.city,
            label = property.label,
            district = property.district,
            neighborhood = property.neighborhood,
            currency = property.currency,
            roomCount = property.roomCount,
            bathCount = property.bathCount,
            price = property.price,
            grossSquareMeters = property.gross,
            netSquareMeters = property.net,
            roomConfiguration = property.room,
            buildingAge = property.buildingAge,
            description = property.description,
            createdDate = property.createdDate,
            images = property.images
        )
    }
}