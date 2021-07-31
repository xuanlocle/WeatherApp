package com.xuanlocle.weatherapp.ui.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.weatherapp.data.model.CityItem
import com.xuanlocle.weatherapp.data.model.WeatherDetailsItem
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.model.TemperatureUnitEnum
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse
import com.xuanlocle.weatherapp.data.repository.WeatherRepository
import com.xuanlocle.weatherapp.ui.base.BaseViewModel
import com.xuanlocle.weatherapp.weatherPreference
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(private val repository: WeatherRepository) : BaseViewModel() {

    private var weatherDetailsResponse = MutableLiveData<WeatherResponse>()
    val weatherDetailsList: LiveData<WeatherResponse>
        get() = weatherDetailsResponse

    private val showLoadingLiveData = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = showLoadingLiveData


    /**
     * Get latest weather by cityname and count
     */
    fun getLatestWeather(
        cityName: String? = null,
        amount: Int? = null,
        units: TemperatureUnitEnum? = null,
    ) {

        if (cityName != null)
            weatherPreference.setLastCitySearch(cityName)

        fetchLatestWeather(
            cityName ?: weatherPreference.getLastCitySearch(),
            amount ?: weatherPreference.getAmountOfDays(),
            units ?: weatherPreference.getUnitTemperature())
    }

    fun fetchLatestWeather(cityName: String, amount: Int, units: TemperatureUnitEnum) {
        showLoadingLiveData.postValue(true)
        uiScope.launch {
            val result = withContext(ioContext) {
                repository.getWeather(cityName, amount, units)
            }
            when (result) {
                is BaseResult.Success -> {
                    weatherDetailsResponse.postValue(result.data)
                }
                is BaseResult.Error -> {
                    showError.postValue(result.error)
                }
            }
            showLoadingLiveData.postValue(false)
        }

    }

    fun getLatestWeatherEntity() {
        uiScope.launch {
            val result = withContext(ioContext) {
                repository.getLatestWeatherItem()
            }
            if (result.isEmpty() || result.firstOrNull() == null) {
                showLoadingLiveData.postValue(false)
                return@launch
            }
            result.firstOrNull().let {
                val listMapped = mutableListOf<WeatherItem>()
                it?.weathers?.forEach {
                    listMapped.add(WeatherItem(dt = it.dt,
                        humidity = it.humidity,
                        pressure = it.pressure,
                        temp = it.temp,
                        weather = listOf(WeatherDetailsItem(description = it.description))
                    ))
                }

                val resp = WeatherResponse(city = CityItem(name = it?.weatherSummary?.cityName
                    ?: ""),
                    list = listMapped,
                    temperatureUnitTemperature = it?.weatherSummary?.temperatureUnitTemperature ?: TemperatureUnitEnum.DEFAULT)
                weatherDetailsResponse.postValue(resp)
                showLoadingLiveData.postValue(false)
            }
        }
    }
}