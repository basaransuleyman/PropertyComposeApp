package com.property.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.property.core.presentation.SharedViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(
    navigator: Navigator,
    homeScreen: @Composable (sharedViewModel: SharedViewModel) -> Unit,
    detailScreen: @Composable (sharedViewModel: SharedViewModel) -> Unit,
) {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()

    LaunchedEffect(Unit) {
        navigator.actions.collectLatest { action ->
            when (action) {
                Navigator.Action.Back -> navController.popBackStack()
                is Navigator.Action.Navigate -> navController.navigate(
                    route = action.destination,
                    builder = action.navOptions
                )
            }
        }
    }

    NavHost(navController, startDestination = Destination.list.route) {
        composable(Destination.list.route) {
            homeScreen(sharedViewModel)
        }
        composable(Destination.detail.route, Destination.detail.arguments) {
            detailScreen(sharedViewModel)
        }
    }

}
