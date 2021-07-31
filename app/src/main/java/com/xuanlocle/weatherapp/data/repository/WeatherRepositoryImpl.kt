package com.xuanlocle.weatherapp.data.repository

import com.xuanlocle.weatherapp.data.db.WeatherDatabase
import com.xuanlocle.weatherapp.data.db.entity.WeatherEntity
import com.xuanlocle.weatherapp.data.db.entity.WeatherListEntity
import com.xuanlocle.weatherapp.data.db.entity.WeatherSummaryEntity
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.remote.api.WeatherService
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse

class WeatherRepositoryImpl(
    private val database: WeatherDatabase,
    private val weatherService: WeatherService,
) : WeatherRepository {

    override suspend fun getWeather(
        cityName: String,
        count: Int,
        units: UnitRequest,
    ): BaseResult<WeatherResponse> {
        return try {
            weatherService.getWeatherFromApiAsync(cityName, count, units)
                .await()
                .let { weatherRes ->
                    if (weatherRes.isSuccess) {
                        weatherRes.apply {
                            unitTemperature = units
                        }

                        insert(cityName, count, units, weatherRes.list ?: listOf())
                        return@let BaseResult.Success(weatherRes)
                    } else {
                        return@let BaseResult.Error("")
                    }
                }
        } catch (ex: Exception) {
            return BaseResult.Error("")
        }
    }


    private suspend fun insert(
        city: String,
        amount: Int,
        units: UnitRequest,
        list: List<WeatherItem>,
    ) {
        val summary = WeatherSummaryEntity(city, amount, units)
        val listMapped = mutableListOf<WeatherEntity>()
        list.forEach {
            listMapped.add(WeatherEntity(it.dt,
                it.temp,
                it.pressure,
                it.humidity,
                it.weather?.firstOrNull()?.description ?: "Nothing",
                city))
        }

        //First have to insert the entities which holds the @Relation
        database.getWeatherDao().insertWeatherSummary(summary)
        //Now you can insert members, because thier foreignKeys (groupId) is already inserted into the Database
        database.getWeatherDao().insertWeathers(listMapped)
    }

//    suspend fun delete(item: WeatherItem) = database.getWeatherDao().delete(item)

    override suspend fun getLatestWeatherItem() = database.getWeatherDao().getLatestWeatherItem()

}