package com.studentrealitylab.app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.studentrealitylab.app.chart.IncomeLineChart
import com.studentrealitylab.app.ui.theme.GoldAccent
import com.studentrealitylab.app.ui.theme.GoldPrimary
import com.studentrealitylab.app.ui.theme.RichBlackElevated
import com.studentrealitylab.app.ui.theme.TextOnDark
import com.studentrealitylab.app.utils.InflationCalculator
import kotlin.math.roundToInt

@Composable
fun StoryScreen(
    uiState: StudentRealityLabUiState,
    onYearSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
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
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AssistChip(
                        onClick = {},
                        label = { Text("Data Story") },
                        leadingIcon = { Icon(Icons.Filled.Diamond, contentDescription = null) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = GoldAccent,
                            labelColor = RichBlackElevated,
                            leadingIconContentColor = RichBlackElevated
                        )
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("Inflation Lens") },
                        leadingIcon = { Icon(Icons.Filled.MonetizationOn, contentDescription = null) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = GoldPrimary,
                            labelColor = RichBlackElevated,
                            leadingIconContentColor = RichBlackElevated
                        )
                    )
                }
                Text(
                    text = "Where is the middle-class income line today after adjusting historical incomes for inflation?",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextOnDark
                )
                Text(
                    text = "Use the year slider to compare nominal income and inflation-adjusted purchasing power.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
        ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Income Trend",
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary
                )
                HorizontalDivider(color = GoldAccent.copy(alpha = 0.45f))
                IncomeLineChart(
                    records = uiState.records,
                    selectedYear = uiState.selectedYear,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
                Text(
                    text = "X-axis: Year   •   Y-axis: Household income (USD)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (uiState.records.isNotEmpty()) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    val years = uiState.records.map { it.year }
                    val selectedIndex = years.indexOf(uiState.selectedYear).takeIf { it >= 0 } ?: 0
                    var sliderValue by remember(uiState.records) { mutableFloatStateOf(selectedIndex.toFloat()) }

                    LaunchedEffect(selectedIndex) {
                        sliderValue = selectedIndex.toFloat()
                    }

                    Text(
                        text = "Selected year: ${years[selectedIndex]}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = GoldAccent
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = GoldPrimary
                        )
                        Text(
                            text = "Drag to inspect each year",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Slider(
                        value = sliderValue,
                        onValueChange = { value ->
                            sliderValue = value
                            val index = value.roundToInt().coerceIn(0, years.lastIndex)
                            onYearSelected(years[index])
                        },
                        valueRange = 0f..years.lastIndex.toFloat(),
                        steps = (years.size - 2).coerceAtLeast(0),
                        modifier = Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.SliderDefaults.colors(
                            thumbColor = GoldAccent,
                            activeTrackColor = GoldPrimary,
                            inactiveTrackColor = GoldPrimary.copy(alpha = 0.28f)
                        )
                    )
                }
            }
        }

        uiState.selectedAdjustedIncome?.let {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(containerColor = RichBlackElevated)
            ) {
                Text(
                    text = "Inflation-adjusted median income for ${uiState.selectedYear}: ${InflationCalculator.formatCurrency(it)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = GoldAccent,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        InsightCallout(text = uiState.calloutText)

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "What to notice: nominal income climbs over time, but inflation-adjusted values show the true movement of middle-class purchasing power.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
