package com.xuanlocle.weatherapp.di

import com.xuanlocle.weatherapp.data.repository.WeatherRepository
import com.xuanlocle.weatherapp.data.repository.WeatherRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repositoryModule = Kodein.Module("repositoryModule") {
    bind<WeatherRepository>() with provider { WeatherRepositoryImpl(instance(), instance()) }

}