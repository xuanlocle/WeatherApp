package com.xuanlocle.weatherapp.data.db.entity

data class CityItem(
    val id: Long,
    val name: String,
    val coord: CoordinateItem,
    val country: String,
    val population: Int,
    val timezone: Int
) {

}
