package com.property.core.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ErrorComponent(errorMessage: String?, modifier: Modifier = Modifier) {
    errorMessage?.let {
        Text(
            text = it,
            color = Color.Red,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = modifier
        )
    }
}