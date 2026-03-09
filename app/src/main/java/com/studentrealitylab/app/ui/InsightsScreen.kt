package com.studentrealitylab.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studentrealitylab.app.ui.theme.GoldAccent
import com.studentrealitylab.app.ui.theme.GoldPrimary
import com.studentrealitylab.app.ui.theme.RichBlackElevated
import com.studentrealitylab.app.utils.InflationCalculator

@Composable
fun InsightsScreen(uiState: StudentRealityLabUiState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Key Insight",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary
                )
                HorizontalDivider(color = GoldAccent.copy(alpha = 0.4f))
                Text(
                    text = uiState.calloutText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        uiState.selectedAdjustedIncome?.let {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Selected Year Snapshot",
                        style = MaterialTheme.typography.titleMedium,
                        color = GoldPrimary
                    )
                    Text(
                        text = "Year: ${uiState.selectedYear}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Adjusted median income: ${InflationCalculator.formatCurrency(it)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldAccent
                    )
                }
            }
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "How to Read This",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary
                )
                Text(
                    text = "- The first line shows nominal median income values by year.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "- The second line adjusts each year to latest-year dollars using CPI.",
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
