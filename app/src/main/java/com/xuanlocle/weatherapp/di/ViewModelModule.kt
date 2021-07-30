package com.xuanlocle.weatherapp.di

import com.xuanlocle.weatherapp.ui.landing.WeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val viewModelModule = Kodein.Module("viewmodelModule") {

    bind() from provider { WeatherViewModelFactory(instance()) }
}