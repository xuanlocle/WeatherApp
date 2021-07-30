package com.xuanlocle.weatherapp.data.db.entity

data class WeatherItem(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TemperatureItem,
    val feels_like: FeelLikeItem,
    val pressure: Int,
    val humidity: Int,
    val weather: List<WeatherDetailsItem>,
    val speed: Float,
    val deg: Float,
    val gust:Float,
    val clouds: Int,
    val pops: Float
) {
}