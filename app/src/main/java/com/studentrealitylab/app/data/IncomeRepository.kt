package com.studentrealitylab.app.data

import android.content.Context
import com.studentrealitylab.app.models.IncomeRecord
import kotlinx.serialization.json.Json

class IncomeRepository(private val context: Context) {

    private val json = Json { ignoreUnknownKeys = true }

    fun loadIncomeRecords(): List<IncomeRecord> {
        val raw = context.assets.open(DATA_FILE_NAME).bufferedReader().use { it.readText() }
        return json.decodeFromString<List<IncomeRecord>>(raw).sortedBy { it.year }
    }

    companion object {
        private const val DATA_FILE_NAME = "income_data.json"
    }
}
