package com.opd.token.shared.core

import com.opd.token.shared.core.analytics.WaitTimeCalculator
import org.junit.Assert.*
import org.junit.Test

class WaitTimeAnalyticsTest {

    @Test
    fun testCalculateEstimatedWaitMinutes_calculatesCorrectForecast() {
        val calculator = WaitTimeCalculator()
        val waitTime3Ahead = calculator.calculateEstimatedWaitMinutes(3, 5)
        val waitTime0Ahead = calculator.calculateEstimatedWaitMinutes(0, 5)

        assertEquals(15, waitTime3Ahead) // 3 * 5 = 15 mins
        assertEquals(0, waitTime0Ahead)  // 0 mins
    }
}
