package com.property.home.data

import com.property.core.network.GenericException
import com.property.home.data.network.HomeApi
import com.property.home.data.network.datasource.GetPropertyListDataSource
import com.property.home.data.network.datasource.GetPropertyListDataSourceImpl
import com.property.home.data.network.model.Property
import com.property.home.data.network.model.PropertyListResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DataSourceUnitTest {

    @Mock
    private lateinit var api: HomeApi
    private lateinit var getPropertiesDataSource: GetPropertyListDataSource

    @Before
    fun setUp() {
        getPropertiesDataSource = GetPropertyListDataSourceImpl(api)
    }

    @Test
    fun `getPropertyList returns property list on successful api call`() = runTest {
        //given
        val mockPropertyList = listOf(
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
        val mockResponse = PropertyListResponse(mockPropertyList)
        `when`(api.getPropertyList()).thenReturn(Response.success(mockResponse))

        //when
        val result = getPropertiesDataSource.getPropertyList()

        //then
        assertNotNull(result)
        assertEquals(mockResponse, result)
    }

    @Test(expected = GenericException::class)
    fun `getPropertyList throws GenericException on api failure`() = runTest {
        `when`(api.getPropertyList()).thenReturn(
            Response.error(
                404,
                okhttp3.ResponseBody.create(null, "")
            )
        )

        // Test call
        getPropertiesDataSource.getPropertyList()
    }
}