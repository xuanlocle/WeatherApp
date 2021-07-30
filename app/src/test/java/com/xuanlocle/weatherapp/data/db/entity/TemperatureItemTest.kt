package com.xuanlocle.weatherapp.data.db.entity

import org.junit.After
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TemperatureItemTest {

    private lateinit var temperatureItemTest: TemperatureItem

    @Before
    fun setup() {
        temperatureItemTest = TemperatureItem(
            27f,
            36f,
            33f,
            36f,
            39f,
            30f
        )
    }

    @After
    fun teardown() {
    }

    @Test
    fun test_getAvgTemp() {
        assertEquals(33.5f, temperatureItemTest.getAvgTemp());
    }
}