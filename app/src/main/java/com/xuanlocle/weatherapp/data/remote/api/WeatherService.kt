package com.xuanlocle.weatherapp.data.remote.api

import com.xuanlocle.weatherapp.BuildConfig
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/daily")
    fun getWeatherFromApi(
        @Query("q") cityName: String,
        @Query("cnt") count: Int,
        @Query("units") unit: UnitRequest,
    ): Deferred<WeatherResponse>

}