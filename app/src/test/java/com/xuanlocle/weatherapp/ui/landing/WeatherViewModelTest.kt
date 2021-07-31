package com.xuanlocle.weatherapp.ui.landing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.xuanlocle.weatherapp.data.remote.api.WeatherService
import com.xuanlocle.weatherapp.data.model.TemperatureUnitEnum
import com.xuanlocle.weatherapp.data.repository.WeatherRepository
import com.xuanlocle.weatherapp.data.repository.WeatherRepositoryImpl
import com.xuanlocle.weatherapp.di.createOkHttpClient
import com.xuanlocle.weatherapp.di.createWebService
import com.xuanlocle.weatherapp.widget.getOrAwaitValue
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.*

class WeatherViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherViewModel
    private lateinit var repository: WeatherRepository
    private lateinit var service: WeatherService

    @Before
    fun setUp() {
        service = createWebService(okHttpClient = createOkHttpClient())
        repository = WeatherRepositoryImpl(service)
        viewModel = WeatherViewModel(repository)
        viewModel.init()
    }

    @After
    fun tearDown() {
    }


    @Test
    fun test_getLatestWeather() {
        runBlocking {
            val lifecycle = LifecycleRegistry(mockk()).apply {
                handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }

            // given
            val cityName = "Saigon"
            val amount = 1
            val unit = TemperatureUnitEnum.DEFAULT
            viewModel.weatherDetailsList.observe({ lifecycle }) {}
            // when
            viewModel.fetchLatestWeather(cityName, amount, unit)

            // then

            print(viewModel.weatherDetailsList.value)
            Assert.assertEquals(viewModel.weatherDetailsList.value, amount)
            Assert.assertEquals(viewModel.weatherDetailsList.getOrAwaitValue(30).city?.name,
                cityName)
        }
    }
}