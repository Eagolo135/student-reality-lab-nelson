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

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Student Reality Lab", style = MaterialTheme.typography.headlineSmall)
                Text(
                    text = "An interactive data story showing how inflation shifts the middle-class income line over time.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Dataset Fields", style = MaterialTheme.typography.titleMedium)
                Text(text = "• year", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• median_income", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• cpi_index", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• real_income (optional)", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Method", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Inflation adjustment uses CPI ratio scaling: adjusted = historicalAmount × (targetCpi / historicalCpi).",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
