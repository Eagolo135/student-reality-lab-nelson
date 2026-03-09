package com.studentrealitylab.app.utils

import com.studentrealitylab.app.models.IncomeRecord
import kotlin.math.roundToInt

object InflationCalculator {

    /**
     * Adjusts a historical amount into target-year dollars using CPI ratio:
     * adjusted = historicalAmount * (targetCpi / historicalCpi)
     */
    fun adjustAmount(historicalAmount: Double, historicalCpi: Double, targetCpi: Double): Double {
        if (historicalCpi <= 0.0) return historicalAmount
        return historicalAmount * (targetCpi / historicalCpi)
    }

    fun adjustToLatest(record: IncomeRecord, latestCpi: Double): Double {
        return record.realIncome ?: adjustAmount(
            historicalAmount = record.medianIncome,
            historicalCpi = record.cpiIndex,
            targetCpi = latestCpi
        )
    }

    fun formatCurrency(value: Double): String {
        return "$" + value.roundToInt().toString().reversed().chunked(3).joinToString(",").reversed()
    }
}
