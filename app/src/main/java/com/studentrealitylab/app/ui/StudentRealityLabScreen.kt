package com.studentrealitylab.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.studentrealitylab.app.ui.theme.GoldAccent
import com.studentrealitylab.app.ui.theme.GoldPrimary
import com.studentrealitylab.app.ui.theme.RichBlack
import com.studentrealitylab.app.ui.theme.RichBlackSoft

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

    Box(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(RichBlackSoft, RichBlack)
            )
        )
    ) {
        Scaffold(
            modifier = Modifier,
            containerColor = androidx.compose.ui.graphics.Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text(text = "Student Reality Lab") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = RichBlackSoft,
                        titleContentColor = GoldPrimary
                    )
                )
            },
            bottomBar = {
                NavigationBar(containerColor = RichBlackSoft) {
                    destinations.forEach { destination ->
                        NavigationBarItem(
                            selected = selectedDestination == destination,
                            onClick = { setSelectedDestination(destination) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = RichBlack,
                                selectedTextColor = GoldPrimary,
                                indicatorColor = GoldAccent,
                                unselectedIconColor = GoldPrimary,
                                unselectedTextColor = GoldPrimary
                            ),
                            icon = {
                                val imageVector = when (destination) {
                                    AppDestination.Story -> Icons.AutoMirrored.Filled.ShowChart
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
}
