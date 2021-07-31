package com.xuanlocle.weatherapp.di

import com.xuanlocle.weatherapp.data.db.WeatherDatabase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val modelModule = Kodein.Module("modelModule") {

    bind() from singleton { WeatherDatabase(instance()) }

}