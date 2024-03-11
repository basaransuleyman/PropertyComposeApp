package com.property.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.property.core.presentation.SharedViewModel
import com.property.core.presentation.components.ErrorComponent
import com.property.core.presentation.components.PropertyListShimmerEffect
import com.property.home.presentation.components.PropertyListScreen
import com.property.home.presentation.uievent.HomeUIEvent

@Composable
fun HomeScreen(sharedViewModel: SharedViewModel) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.onEvent(HomeUIEvent.LoadInitialHome)
    }

    when {
        state.isLoading -> PropertyListShimmerEffect()
        state.error != null -> ErrorComponent(state.error!!.message, Modifier)
        state.propertyList != null -> PropertyListScreen(
            propertyList = state.propertyList!!,
            viewModel::onEvent,
            sharedViewModel,
            viewModel = viewModel
        )
    }
}


