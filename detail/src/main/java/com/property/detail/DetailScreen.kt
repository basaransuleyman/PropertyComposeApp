package com.property.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.property.core.model.Property
import com.property.core.presentation.SharedViewModel
import com.property.core.presentation.components.CoilImageComponent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(sharedViewModel: SharedViewModel) {
    val property = sharedViewModel.property
    val imageOverlapSize = 48.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = White
                    )
                },
                actions = {
                    IconButton(onClick = { /*  */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "Share",
                            tint = White
                        )
                    }
                    IconButton(onClick = { /*   */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "Favourite",
                            tint = White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Transparent)
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            property?.let {
                CoilImageComponent(
                    imageUrl = property.images.first(),
                    contentDescription = "Property Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp)
                        .background(
                            color = White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "1/${property.images.size}",
                        color = Black,
                        fontSize = 12.sp
                    )
                }

                Card(
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .offset(y = (-50).dp)
                )  {
                    Column(
                        modifier = Modifier
                            .background(
                                color = White,
                                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                            )
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        PropertyDetailContent(property)
                    }
                }
            }
        }
    }
}


@Composable
fun PropertyDetailContent(property: Property) {
     Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = property.category,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = property.createdDate,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = property.price,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.ExtraBold
        )
        PropertyDetailRow("Oda Sayısı", property.roomCount.toString())
        PropertyDetailRow("Bina Yaşı", property.buildingAge)
        PropertyDetailRow("Brüt Alan", property.grossSquareMeters.toString())
        PropertyDetailRow("Net Alan", property.netSquareMeters.toString())
        Text(
            text = "Açıklama",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "enim lacinia pellentesque. Proin porttitor, turpis et " +
                    "enim lacinia pellentesque. Proin porttitor, turpis et " +
                    "enim lacinia pellentesque. Proin porttitor, turpis e",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun PropertyDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
    Divider()
}
