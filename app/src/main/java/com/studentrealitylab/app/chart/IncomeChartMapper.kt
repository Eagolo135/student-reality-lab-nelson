package com.studentrealitylab.app.chart

import com.github.mikephil.charting.data.Entry
import com.studentrealitylab.app.models.IncomeRecord
import com.studentrealitylab.app.utils.InflationCalculator

data class IncomeSeries(
    val medianIncome: List<Entry>,
    val inflationAdjustedIncome: List<Entry>
)

object IncomeChartMapper {
    fun map(records: List<IncomeRecord>): IncomeSeries {
        val latestCpi = records.lastOrNull()?.cpiIndex ?: 0.0
        return IncomeSeries(
            medianIncome = records.map { Entry(it.year.toFloat(), it.medianIncome.toFloat()) },
            inflationAdjustedIncome = records.map {
                Entry(
                    it.year.toFloat(),
                    InflationCalculator.adjustToLatest(it, latestCpi).toFloat()
                )
            }
        )
    }
}
