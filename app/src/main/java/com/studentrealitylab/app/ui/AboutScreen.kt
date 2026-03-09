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

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
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
                    text = "Student Reality Lab",
                    style = MaterialTheme.typography.headlineSmall,
                    color = GoldPrimary
                )
                HorizontalDivider(color = GoldAccent.copy(alpha = 0.4f))
                Text(
                    text = "An interactive data story showing how inflation shifts the middle-class income line over time.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Dataset Fields",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary
                )
                Text(text = "• year", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• median_income", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• cpi_index", style = MaterialTheme.typography.bodyMedium)
                Text(text = "• real_income (optional)", style = MaterialTheme.typography.bodyMedium)
            }
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Method",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary
                )
                Text(
                    text = "Inflation adjustment uses CPI ratio scaling: adjusted = historicalAmount × (targetCpi / historicalCpi).",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
