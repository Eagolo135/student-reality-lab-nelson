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
                legend.textColor = Color.parseColor("#FFD166")
                axisRight.isEnabled = false
                setBackgroundColor(Color.parseColor("#1C1C24"))
                axisLeft.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "$${(value / 1000).toInt()}k"
                    }
                }
                axisLeft.textColor = Color.parseColor("#D4AF37")
                axisLeft.gridColor = Color.parseColor("#3A3A48")
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.textColor = Color.parseColor("#D4AF37")
                xAxis.gridColor = Color.parseColor("#2C2C36")
                xAxis.valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String = value.toInt().toString()
                }
            }
        },
        update = { chart ->
            val series = IncomeChartMapper.map(records)

            val medianDataSet = LineDataSet(series.medianIncome, "Median income (nominal)").apply {
                color = Color.parseColor("#FFD166")
                lineWidth = 2.5f
                setDrawCircles(false)
                setDrawValues(false)
            }

            val adjustedDataSet = LineDataSet(
                series.inflationAdjustedIncome,
                "Inflation-adjusted income"
            ).apply {
                color = Color.parseColor("#D4AF37")
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
