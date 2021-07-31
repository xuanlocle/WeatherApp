package com.xuanlocle.weatherapp.data.db.entity

import com.xuanlocle.weatherapp.data.model.TemperatureItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import org.junit.After
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
            30f, UnitRequest.DEFAULT
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