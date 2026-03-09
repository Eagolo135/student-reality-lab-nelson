package com.studentrealitylab.app.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StudentRealityLabScreen(
    viewModel: StudentRealityLabViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val destinations = remember {
        listOf(AppDestination.Story, AppDestination.Insights, AppDestination.About)
    }
    val (selectedDestination, setSelectedDestination) = rememberSaveable {
        mutableStateOf(AppDestination.Story)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Student Reality Lab") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        },
        bottomBar = {
            NavigationBar {
                destinations.forEach { destination ->
                    NavigationBarItem(
                        selected = selectedDestination == destination,
                        onClick = { setSelectedDestination(destination) },
                        icon = {
                            val imageVector = when (destination) {
                                AppDestination.Story -> Icons.Filled.ShowChart
                                AppDestination.Insights -> Icons.Filled.Insights
                                AppDestination.About -> Icons.Filled.Info
                            }
                            Icon(imageVector = imageVector, contentDescription = destination.label)
                        },
                        label = { Text(text = destination.label) }
                    )
                }
            }
        }
    ) { contentPadding ->
        when (selectedDestination) {
            AppDestination.Story -> StoryScreen(
                uiState = uiState,
                onYearSelected = viewModel::onYearSelected,
                modifier = Modifier.padding(contentPadding)
            )

            AppDestination.Insights -> InsightsScreen(
                uiState = uiState,
                modifier = Modifier.padding(contentPadding)
            )

            AppDestination.About -> AboutScreen(
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}
