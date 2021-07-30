package com.xuanlocle.weatherapp.data.remote.response

import com.xuanlocle.weatherapp.data.db.entity.CityItem
import com.xuanlocle.weatherapp.data.db.entity.WeatherItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest

data class WeatherResponse(
    val cod: Int?,
    val message: Float?,

    val city: CityItem?,
    val cnt: Int?,
    val list: List<WeatherItem>?,

    var unitTemperature: UnitRequest = UnitRequest.DEFAULT
) {
    val isSuccess: Boolean
        get() = cod == 200
}