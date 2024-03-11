package com.property.home.presentation.uievent

sealed interface HomeUIEvent {
    data object LoadInitialHome : HomeUIEvent
    data object Dismiss : HomeUIEvent
    data object OnPropertyClicked : HomeUIEvent
}