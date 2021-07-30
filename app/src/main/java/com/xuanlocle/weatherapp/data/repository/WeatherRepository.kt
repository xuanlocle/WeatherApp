package com.xuanlocle.weatherapp.data.repository

import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse

interface WeatherRepository {

    suspend fun getWeather(
        cityName: String,
        count: Int,
        units: UnitRequest
    ): BaseResult<WeatherResponse>

}
