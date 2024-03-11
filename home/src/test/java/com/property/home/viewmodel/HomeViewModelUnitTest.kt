package com.property.home.viewmodel

import com.property.core.model.Property
import com.property.core.navigation.NavigationService
import com.property.home.domain.usecase.GetPropertyListUseCase
import com.property.home.presentation.HomeViewModel
import com.property.home.presentation.state.HomeUIState
import com.property.home.presentation.uievent.HomeUIEvent
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var getPropertyListUseCase: GetPropertyListUseCase
    @Mock
    private lateinit var navigator: NavigationService

    private lateinit var viewModel: HomeViewModel

    private val propertyList = listOf(
        Property(
            id = "1",
            category = "Category1",
            city = "City1",
            label = "For Sale",
            district = "District1",
            neighborhood = "Neighborhood1",
            currency = "USD",
            roomCount = 3,
            bathCount = 2,
            price = "300000",
            grossSquareMeters = 120,
            netSquareMeters = 110,
            roomConfiguration = "3+1",
            buildingAge = "10 years",
            description = "A beautiful and spacious apartment in a prime location.",
            createdDate = "2023-03-01",
            images = listOf("image1.jpg", "image2.jpg")
        ),
        Property(
            id = "2",
            category = "Category2",
            city = "City2",
            label = "For Rent",
            district = "District2",
            neighborhood = "Neighborhood2",
            currency = "EUR",
            roomCount = 2,
            bathCount = 1,
            price = "1500",
            grossSquareMeters = 80,
            netSquareMeters = 75,
            roomConfiguration = "2+1",
            buildingAge = "5 years",
            description = "Modern apartment with all amenities in a quiet neighborhood.",
            createdDate = "2023-03-05",
            images = listOf("image3.jpg", "image4.jpg")
        )
    )
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(getPropertyListUseCase, navigator)
    }

    @Test
    fun `loadInitialHome updates uiState correctly`() = runTest {
        `when`(getPropertyListUseCase.getPropertyList()).thenReturn(flowOf(propertyList))

        val stateList = mutableListOf<HomeUIState>()
        val job = launch {
            viewModel.uiState.toList(stateList)
        }

        viewModel.handleEvent(HomeUIEvent.LoadInitialHome)

        advanceUntilIdle()

        assertTrue("Expected state not found in stateList", stateList.any {
            it.propertyList == propertyList && !it.isLoading
        })

        job.cancel()
    }

    @Test
    fun `searchQuery filters propertyList correctly`() = runTest {
        `when`(getPropertyListUseCase.getPropertyList()).thenReturn(flowOf(propertyList))

        viewModel.getPropertyList()

        advanceUntilIdle()

        viewModel.searchQuery.value = "Category1"

        advanceTimeBy(1000)

        val currentState = viewModel.uiState.value
        assertEquals(2, currentState.propertyList?.size)
        assertEquals("Category1", currentState.propertyList?.first()?.category)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}