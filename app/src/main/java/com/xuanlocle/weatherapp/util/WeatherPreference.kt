package com.xuanlocle.weatherapp.util

import android.content.Context
import android.content.SharedPreferences
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest

class WeatherPreference(context: Context) {

    companion object {
        const val LAST_CITY_SEARCH = "LAST_CITY_SEARCH"
        const val AMOUNT_OF_DAYS = "AMOUNT_OF_DAYS"
        const val UNIT_TEMPERATURE_REQUEST = "UNIT_TEMPERATURE_REQUEST"
    }

    private var sp: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private fun clear(key: String) {
        sp.edit().remove(key).apply()
    }

    fun clear(keys: List<String>) {
        keys.forEach {
            clear(it)
        }
    }

    fun setLastCitySearch(cityName: String) {
        sp.edit().putString(LAST_CITY_SEARCH, cityName).apply()
    }

    fun getLastCitySearch(): String {
        return sp.getString(LAST_CITY_SEARCH, "Saigon") ?: "Saigon"
    }

    fun setAmountOfDays(amount: Int) {
        sp.edit().putInt(AMOUNT_OF_DAYS, amount).apply()
    }

    fun getAmountOfDays(): Int {
        return sp.getInt(AMOUNT_OF_DAYS, 7)
    }

    fun setUnitTemperature(unit: UnitRequest) {
        sp.edit().putString(UNIT_TEMPERATURE_REQUEST, unit.name).apply()
    }

    fun getUnitTemperature(): UnitRequest {
        return when (sp.getString(UNIT_TEMPERATURE_REQUEST, "")) {
            UnitRequest.METRIC.name -> UnitRequest.METRIC
            UnitRequest.IMPERIAL.name -> UnitRequest.IMPERIAL
            UnitRequest.DEFAULT.name -> UnitRequest.DEFAULT
            else -> UnitRequest.DEFAULT
        }
    }

}