package com.property.navigation.utils

import androidx.navigation.NamedNavArgument

interface NodeScreen {
    val route: String
    val arguments: List<NamedNavArgument>
}