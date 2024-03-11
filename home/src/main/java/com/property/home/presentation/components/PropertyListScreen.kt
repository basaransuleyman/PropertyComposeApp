package com.property.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.property.core.model.Property
import com.property.home.presentation.uievent.HomeUIEvent
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.property.core.presentation.SharedViewModel
import com.property.home.presentation.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertyListScreen(
    propertyList: List<Property>,
    onPropertyClicked: (HomeUIEvent) -> Unit,
    sharedViewModel: SharedViewModel,
    viewModel: HomeViewModel
) {
    Column(modifier = Modifier.background(Color(0xFF002B49))) {
        Spacer(modifier = Modifier.height(8.dp))
        TopAppBar(
            title = {
                SearchBar(viewModel = viewModel)
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF002B49)),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
            ),
        ) {
            PropertyList(
                propertyList = propertyList,
                onPropertyClicked = onPropertyClicked,
                sharedViewModel
            )
        }
    }
}


@Composable
fun PropertyList(
    propertyList: List<Property>,
    onPropertyClicked: (HomeUIEvent) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LazyColumn {
        item { Spacer(Modifier.height(16.dp)) }
        items(items = propertyList, key = { property ->
            //for unique id, cause we don't have id on response
            "${property.city}-${property.price}"
        }) { property ->
            PropertyCard(property = property, onPropertyClicked, sharedViewModel)
        }
    }
}
