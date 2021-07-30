package com.xuanlocle.weatherapp.data.repository

import com.xuanlocle.weatherapp.data.remote.api.WeatherService
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse
import java.lang.Exception

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {

    override suspend fun getWeather(
        cityName: String,
        count: Int,
        units: UnitRequest,
    ): BaseResult<WeatherResponse> {
        var data: WeatherResponse?
        weatherService.getWeatherFromApi(cityName, count, units)
            .await()
            .let {
                if (it.isSuccess) {
                    data = it.apply { it.unitTemperature = units }
                } else {
                    return BaseResult.Error(Exception(it.message.toString()).toString())
                }
            }
        return BaseResult.Success(data)
    }

}