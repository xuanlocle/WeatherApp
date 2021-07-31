package com.xuanlocle.weatherapp.data.repository

import com.xuanlocle.weatherapp.data.db.entity.WeatherListEntity
import com.xuanlocle.weatherapp.data.model.TemperatureUnitEnum
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse

interface WeatherRepository {

    suspend fun getWeather(
        cityName: String,
        count: Int,
        units: TemperatureUnitEnum
    ): BaseResult<WeatherResponse>

    suspend fun getLatestWeatherItem() : List<WeatherListEntity>
}
