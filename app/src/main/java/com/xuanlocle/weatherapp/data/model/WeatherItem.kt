package com.xuanlocle.weatherapp.data.model


data class WeatherItem(
    val dt: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: TemperatureItem? = null,
    val feels_like: FeelLikeItem? = null,
    val pressure: Int? = null,
    val humidity: Int? = null,
    val weather: List<WeatherDetailsItem>? = null,
    val speed: Float? = null,
    val deg: Float? = null,
    val gust: Float? = null,
    val clouds: Int? = null,
    val pops: Float? = null,
)