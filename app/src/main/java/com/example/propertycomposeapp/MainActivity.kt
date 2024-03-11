package com.example.propertycomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.propertycomposeapp.ui.theme.PropertyComposeAppTheme
import com.property.detail.DetailScreen
import com.property.home.presentation.HomeScreen
import com.property.navigation.AppNavigation
import com.property.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PropertyComposeAppTheme {
                AppNavigation(
                    navigator = navigator,
                    homeScreen = {
                        HomeScreen(it)
                    },
                    detailScreen = {
                        DetailScreen(it)
                    }
                )
            }
        }
    }
}