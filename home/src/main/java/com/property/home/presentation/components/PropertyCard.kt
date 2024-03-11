package com.property.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.property.core.presentation.components.CoilImageComponent
import com.property.home.R
import com.property.core.model.Property
import com.property.core.presentation.SharedViewModel
import com.property.home.presentation.HomeViewModel
import com.property.home.presentation.uievent.HomeUIEvent
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyCard(
    property: Property,
    onPropertyClicked: (HomeUIEvent) -> Unit,
    sharedViewModel: SharedViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        onClick = {
            onPropertyClicked(HomeUIEvent.OnPropertyClicked)
            sharedViewModel.addProperty(property)
        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box {
            Column {
                CoilImageComponent(
                    imageUrl = property.images.first(),
                    contentDescription = "Property Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                PropertyDetails(property = property)
            }
            FavoriteIcon(modifier = Modifier.align(Alignment.TopEnd))
            LabelBadge(
                label = property.label,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun FavoriteIcon(modifier: Modifier) {
    Box(
        modifier = modifier
            .size(36.dp)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.heart),
            contentDescription = "Filter Icon",
            tint = Color.White
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: HomeViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()

    Row(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { newValue ->
                viewModel.searchQuery.value = newValue
            }, modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(
                    Color.White,
                    RoundedCornerShape(32.dp)
                ),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp
            ), leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Black)
            },
            placeholder = { Text("İstanbul, Beykoz", fontSize = 12.sp) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(cursorColor = Color.Black)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Card(
            modifier = Modifier.size(48.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            IconButton(
                onClick = { /* TODO: Filter action */ },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filter_svg),
                    contentDescription = "Filter Icon",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun LabelBadge(label: String?, modifier: Modifier) {
    label?.let {
        Box(
            modifier = modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = it.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PropertyDetails(property: Property) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${property.price} TL",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = property.createdDate,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }
        Text(
            text = property.city,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Row {
            Text(
                text = "${property.roomCount} oda ${property.bathCount} banyo",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${property.grossSquareMeters} brüt m² ${property.netSquareMeters} net m²",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
