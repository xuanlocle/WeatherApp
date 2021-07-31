package com.xuanlocle.weatherapp.ui.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
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
        units: UnitRequest? = null,
    ) {

        if (cityName != null)
            weatherPreference.setLastCitySearch(cityName)

        fetchLatestWeather(
            cityName ?: weatherPreference.getLastCitySearch(),
            amount ?: weatherPreference.getAmountOfDays(),
            units ?: weatherPreference.getUnitTemperature())
    }

    fun fetchLatestWeather(cityName: String, amount: Int, units: UnitRequest) {
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
//                    showError.value = result.errorMessage
                }
            }
            showLoadingLiveData.postValue(false)
        }
    }

}