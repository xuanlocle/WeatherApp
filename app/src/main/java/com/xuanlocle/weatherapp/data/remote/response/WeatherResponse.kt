package com.xuanlocle.weatherapp.data.remote.response

import com.xuanlocle.weatherapp.data.model.CityItem
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.model.TemperatureUnitEnum

data class WeatherResponse(
    val cod: String? = null,
    val message: Any? = null,

    val city: CityItem? = null,
    val cnt: Int? = null,
    val list: List<WeatherItem>? = null,

    var temperatureUnitTemperature: TemperatureUnitEnum = TemperatureUnitEnum.DEFAULT,
) {
    val isSuccess: Boolean
        get() = cod == "200"
}