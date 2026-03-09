package com.studentrealitylab.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.studentrealitylab.app.chart.IncomeLineChart
import com.studentrealitylab.app.utils.InflationCalculator
import kotlin.math.roundToInt

@Composable
fun StudentRealityLabScreen(
    viewModel: StudentRealityLabViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Student Reality Lab",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Where is the middle-class income line today after adjusting historical incomes for inflation?",
            style = MaterialTheme.typography.titleMedium
        )

        IncomeLineChart(
            records = uiState.records,
            selectedYear = uiState.selectedYear,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        if (uiState.records.isNotEmpty()) {
            val years = uiState.records.map { it.year }
            val selectedIndex = years.indexOf(uiState.selectedYear).takeIf { it >= 0 } ?: 0
            var sliderValue by remember(uiState.records) { mutableFloatStateOf(selectedIndex.toFloat()) }

            LaunchedEffect(selectedIndex) {
                sliderValue = selectedIndex.toFloat()
            }

            Text(
                text = "Selected year: ${years[selectedIndex]}",
                style = MaterialTheme.typography.bodyLarge
            )

            Slider(
                value = sliderValue,
                onValueChange = { value ->
                    sliderValue = value
                    val index = value.roundToInt().coerceIn(0, years.lastIndex)
                    viewModel.onYearSelected(years[index])
                },
                valueRange = 0f..years.lastIndex.toFloat(),
                steps = (years.size - 2).coerceAtLeast(0),
                modifier = Modifier.fillMaxWidth()
            )
        }

        uiState.selectedAdjustedIncome?.let {
            Text(
                text = "Inflation-adjusted median income for ${uiState.selectedYear}: ${InflationCalculator.formatCurrency(it)}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        InsightCallout(text = uiState.calloutText)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "What to notice: the nominal line rises over time, but inflation-adjusted values reveal how purchasing power shifts the middle-class income line.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
