package com.studentrealitylab.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studentrealitylab.app.utils.InflationCalculator

@Composable
fun InsightsScreen(uiState: StudentRealityLabUiState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Key Insight", style = MaterialTheme.typography.titleMedium)
                Text(text = uiState.calloutText, style = MaterialTheme.typography.bodyLarge)
            }
        }

        uiState.selectedAdjustedIncome?.let {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(text = "Selected Year Snapshot", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = "Year: ${uiState.selectedYear}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Adjusted median income: ${InflationCalculator.formatCurrency(it)}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "How to Read This", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "- The blue line shows nominal median income values by year.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- The green line adjusts each year to latest-year dollars using CPI.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- The gap between lines reflects inflation effects on purchasing power.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
