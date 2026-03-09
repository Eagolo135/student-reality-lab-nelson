package com.studentrealitylab.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.studentrealitylab.app.data.IncomeRepository
import com.studentrealitylab.app.models.IncomeRecord
import com.studentrealitylab.app.utils.InflationCalculator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class StudentRealityLabUiState(
    val records: List<IncomeRecord> = emptyList(),
    val selectedYear: Int? = null,
    val selectedAdjustedIncome: Double? = null,
    val calloutText: String = ""
)

class StudentRealityLabViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = IncomeRepository(application.applicationContext)
    private val _uiState = MutableStateFlow(StudentRealityLabUiState())
    val uiState: StateFlow<StudentRealityLabUiState> = _uiState.asStateFlow()

    init {
        val records = repository.loadIncomeRecords()
        if (records.isNotEmpty()) {
            val initialYear = records.first().year
            _uiState.value = StudentRealityLabUiState(records = records)
            onYearSelected(initialYear)
        }
    }

    /**
     * Slider interaction controls the selected year and recomputes the highlighted
     * inflation-adjusted value shown in chart context and annotation text.
     */
    fun onYearSelected(year: Int) {
        val records = _uiState.value.records
        if (records.isEmpty()) return

        val selectedRecord = records.firstOrNull { it.year == year } ?: return
        val latestCpi = records.last().cpiIndex
        val adjusted = InflationCalculator.adjustToLatest(selectedRecord, latestCpi)

        val base1990 = records.firstOrNull { it.year == 1990 } ?: records.first()
        val hundredKToday = InflationCalculator.adjustAmount(
            historicalAmount = 100000.0,
            historicalCpi = base1990.cpiIndex,
            targetCpi = latestCpi
        )

        _uiState.value = _uiState.value.copy(
            selectedYear = year,
            selectedAdjustedIncome = adjusted,
            calloutText = "A $100,000 salary in ${base1990.year} has the purchasing power of approximately ${InflationCalculator.formatCurrency(hundredKToday)} today."
        )
    }
}
