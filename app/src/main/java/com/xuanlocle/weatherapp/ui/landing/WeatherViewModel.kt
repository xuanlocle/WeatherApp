package com.xuanlocle.weatherapp.ui.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.weatherapp.data.db.entity.WeatherItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.data.remote.response.BaseResult
import com.xuanlocle.weatherapp.data.remote.response.WeatherResponse
import com.xuanlocle.weatherapp.data.repository.WeatherRepository
import com.xuanlocle.weatherapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(private val repository: WeatherRepository) : BaseViewModel() {

    private val weatherDetailsResponse = MutableLiveData<WeatherResponse>()
    val weatherDetailsList: LiveData<WeatherResponse>
        get() = weatherDetailsResponse

    private val showLoadingLiveData = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = showLoadingLiveData

    /**
     * Get latest weather by cityname and count
     */
    fun getLatestWeather(cityName: String, cnt: Int, units: UnitRequest = UnitRequest.DEFAULT) {
        showLoadingLiveData.postValue(true)
        uiScope.launch {
            val result = withContext(ioContext) {
                repository.getWeather(cityName, cnt, units)
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