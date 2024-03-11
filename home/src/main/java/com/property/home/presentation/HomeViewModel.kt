package com.property.home.presentation

import androidx.lifecycle.viewModelScope
import com.property.core.model.Property
import com.property.core.navigation.NavigationService
import com.property.core.presentation.BaseViewModel
import com.property.home.domain.usecase.GetPropertyListUseCase
import com.property.home.presentation.state.HomeUIState
import com.property.home.presentation.uievent.HomeUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPropertyListUseCase: GetPropertyListUseCase,
    private val navigator: NavigationService
) : BaseViewModel<HomeUIState, HomeUIEvent>(HomeUIState()) {

    val searchQuery = MutableStateFlow("")
    private val fullPropertyList = mutableListOf<Property>()

    init {
        viewModelScope.launch {
            getPropertyList()
            searchQuery
                .debounce(1000)
                .distinctUntilChanged()
                .collect { query ->
                    val filteredList = if (query.isEmpty()) {
                        fullPropertyList
                    } else {
                        fullPropertyList.filter {
                            it.category.contains(query, ignoreCase = true)
                        }
                    }
                    updateUiState {
                        copy(propertyList = filteredList)
                    }
                }
        }
    }

    private suspend fun getPropertyList() {
        getPropertyListUseCase.getPropertyList()
            .onStart {
                updateUiState { copy(isLoading = true) }
            }
            .catch { error ->
                updateUiState { copy(error = error) }
            }
            .collect { propertyList ->
                fullPropertyList.clear()
                fullPropertyList.addAll(propertyList)
                updateUiState {
                    copy(
                        propertyList = propertyList,
                        isLoading = false,
                        error = null
                    )
                }
            }
    }

    override suspend fun handleEvent(event: HomeUIEvent) {
        when (event) {
            HomeUIEvent.LoadInitialHome -> {
                getInitialHome()
            }

            is HomeUIEvent.OnPropertyClicked -> {
                navigateToPropertyDetail()
            }

            is HomeUIEvent.Dismiss -> {
                //handleBack()
            }
        }
    }

    private fun getInitialHome() {
        viewModelScope.launch {
            getPropertyListUseCase.getPropertyList()
                .onStart {
                    updateUiState { copy(isLoading = true) }
                }
                .catch { error ->
                    updateUiState { copy(error = error) }
                }
                .collect { propertyList ->
                    updateUiState {
                        copy(
                            propertyList = propertyList,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    private fun navigateToPropertyDetail() {
        navigator.navigateTo( "detail") {
            launchSingleTop = true
            restoreState = true
        }
    }

}