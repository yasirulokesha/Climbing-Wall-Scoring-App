package com.example.climbingwallscoreboard

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class UnitTests {

    @Test
    fun climbScoreIncrease() {
        assertEquals(1, climb(0))
        assertEquals(3, climb(2))
        assertEquals(7, climb(5))
        assertEquals(13, climb(10))
    }

    @Test
    fun fallScoreDecreases() {
        assertEquals(0, fall(2))
        assertEquals(0, fall(3))
        assertEquals(2, fall(5))
    }
}
