package com.xuanlocle.weatherapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest

@Entity(tableName = "table_weather_summary")
data class WeatherSummaryEntity(
    @PrimaryKey()
    @ColumnInfo(name = "item_city_name")
    var cityName: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int? = null,
    @ColumnInfo(name = "item_unit")
    var unitTemperature: UnitRequest = UnitRequest.DEFAULT,
    @ColumnInfo(name = "id", index = true)
    val id: Int? = null,
)