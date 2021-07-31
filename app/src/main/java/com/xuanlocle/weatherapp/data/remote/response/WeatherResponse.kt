package com.xuanlocle.weatherapp.data.remote.response

import com.xuanlocle.weatherapp.data.model.CityItem
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest

data class WeatherResponse(
    val cod: String? = null,
    val message: Float? = null,

    val city: CityItem? = null,
    val cnt: Int? = null,
    val list: List<WeatherItem>? = null,

    var unitTemperature: UnitRequest = UnitRequest.DEFAULT,
) {
    val isSuccess: Boolean
        get() = cod == "200"
}