package com.property.core.presentation.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
@Composable
fun ShimmerPropertyCard() {
    val shimmerBrush = shimmerAnimationEffect()
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Column(modifier = Modifier.background(brush = shimmerBrush)) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}



@Composable
fun shimmerAnimationEffect(): Brush {
    val gradientWidth = 200f
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val xShimmer = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.toPx() } + gradientWidth,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    return Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.3f),
            Color.LightGray.copy(alpha = 0.7f),
            Color.LightGray.copy(alpha = 0.3f)
        ),
        start = Offset(xShimmer.value - gradientWidth, 0f),
        end = Offset(xShimmer.value, 0f)
    )
}

@Composable
fun PropertyListShimmerEffect() {
    LazyColumn {
        items(5) {
            ShimmerPropertyCard()
        }
    }
}