package com.xuanlocle.weatherapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WeatherListEntity(
    @Embedded
    var weatherSummary: WeatherSummaryEntity,
    @Relation(
        parentColumn = "item_city_name",
        entityColumn = "item_city_name_ref"
    )
    var weathers: List<WeatherEntity>?,
){

}