package com.xuanlocle.weatherapp.data.db

import androidx.room.*
import com.xuanlocle.weatherapp.data.db.entity.WeatherEntity
import com.xuanlocle.weatherapp.data.db.entity.WeatherListEntity
import com.xuanlocle.weatherapp.data.db.entity.WeatherSummaryEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherSummary(item: WeatherSummaryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeathers(items: List<WeatherEntity>)

    @Delete
    suspend fun delete(item: WeatherSummaryEntity)

    @Transaction
    @Query("SELECT * FROM table_weather_summary ORDER BY id DESC LIMIT 1")
    suspend fun getLatestWeatherItem(): List<WeatherListEntity>
}