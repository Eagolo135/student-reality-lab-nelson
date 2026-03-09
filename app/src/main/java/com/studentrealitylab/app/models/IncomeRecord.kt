package com.studentrealitylab.app.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IncomeRecord(
    val year: Int,
    @SerialName("median_income") val medianIncome: Double,
    @SerialName("cpi_index") val cpiIndex: Double,
    @SerialName("real_income") val realIncome: Double? = null
)
