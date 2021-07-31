package com.xuanlocle.weatherapp.data.db.entity

import androidx.room.*
import com.xuanlocle.weatherapp.data.model.TemperatureItem

@Entity(
    tableName = "table_weather_list",
    foreignKeys = [
        ForeignKey(
            entity = WeatherSummaryEntity::class,
            parentColumns = arrayOf("item_city_name"),
            childColumns = arrayOf("item_city_name_ref"),
            onDelete = ForeignKey.CASCADE
        )
    ])
data class WeatherEntity(
    @ColumnInfo(name = "item_date_time")
    val dt: Long?,
    @Embedded
    val temp: TemperatureItem?,
    @ColumnInfo(name = "item_pressure")
    val pressure: Int?,
    @ColumnInfo(name = "item_humidity")
    val humidity: Int?,
    @ColumnInfo(name = "item_description")
    val description: String?,
    @ColumnInfo(index = true, name = "item_city_name_ref")
    var cityNameRef: String? = null,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}