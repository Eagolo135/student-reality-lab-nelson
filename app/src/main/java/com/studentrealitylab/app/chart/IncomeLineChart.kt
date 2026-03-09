package com.studentrealitylab.app.chart

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.studentrealitylab.app.models.IncomeRecord

@Composable
fun IncomeLineChart(
    records: List<IncomeRecord>,
    selectedYear: Int?,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(false)
                setPinchZoom(false)
                legend.isEnabled = true
                axisRight.isEnabled = false
                axisLeft.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "$${(value / 1000).toInt()}k"
                    }
                }
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String = value.toInt().toString()
                }
            }
        },
        update = { chart ->
            val series = IncomeChartMapper.map(records)

            val medianDataSet = LineDataSet(series.medianIncome, "Median income (nominal)").apply {
                color = Color.parseColor("#1E88E5")
                lineWidth = 2.5f
                setDrawCircles(false)
                setDrawValues(false)
            }

            val adjustedDataSet = LineDataSet(
                series.inflationAdjustedIncome,
                "Inflation-adjusted income"
            ).apply {
                color = Color.parseColor("#43A047")
                lineWidth = 2.5f
                setDrawCircles(false)
                setDrawValues(false)
            }

            chart.data = LineData(medianDataSet, adjustedDataSet)

            if (selectedYear != null) {
                chart.highlightValue(selectedYear.toFloat(), 1)
            } else {
                chart.highlightValues(null)
            }

            chart.invalidate()
        }
    )
}
