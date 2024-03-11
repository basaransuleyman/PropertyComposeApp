package com.property.home.domain

import com.property.home.data.domainimpl.mapper.toDomainModel
import com.property.home.data.domainimpl.usecase.GetPropertyListUseCaseImpl
import com.property.home.data.network.datasource.GetPropertyListDataSource
import com.property.home.data.network.model.Property
import com.property.home.data.network.model.PropertyListResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetPropertyListUseCaseUnitTest {
    @Mock
    private lateinit var dataSource: GetPropertyListDataSource

    private lateinit var getPropertyListUseCase: GetPropertyListUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dataSource = mock()
        getPropertyListUseCase = GetPropertyListUseCaseImpl(dataSource, Dispatchers.Unconfined)
    }

    @Test
    fun `getDetail returns correct data`() = runTest {
        // Arrange
        val mockPropertyListResponse = PropertyListResponse(
            data = listOf(
                Property(
                    category = "Konut",
                    city = "İstanbul",
                    label = "Satılık",
                    district = "Beşiktaş",
                    neighborhood = "Etiler",
                    currency = "TL",
                    roomCount = 3,
                    bathCount = 1,
                    price = "9500000",
                    gross = 100,
                    net = 85,
                    room = "3+1",
                    buildingAge = "0",
                    description = "Deniz manzaralı lüks daire",
                    createdDate = "01-01-2024",
                    images = listOf("image_url_1", "image_url_2")
                )
            )
        )

        Mockito.`when`(dataSource.getPropertyList()).thenReturn(mockPropertyListResponse)

        // Act
        val result = getPropertyListUseCase.getPropertyList().first()

        // Assert
        assertEquals(mockPropertyListResponse.toDomainModel(), result)
        Mockito.verify(dataSource).getPropertyList()
    }

}
