package com.studentrealitylab.app.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class InflationCalculatorTest {

    @Test
    fun adjustAmount_usesCpiRatio() {
        val adjusted = InflationCalculator.adjustAmount(
            historicalAmount = 100000.0,
            historicalCpi = 130.7,
            targetCpi = 312.0
        )

        assertEquals(238714.61, adjusted, 0.1)
    }
}
