package com.xuanlocle.weatherapp

import androidx.multidex.MultiDexApplication
import com.xuanlocle.weatherapp.di.modelModule
import com.xuanlocle.weatherapp.di.appModule
import com.xuanlocle.weatherapp.di.repositoryModule
import com.xuanlocle.weatherapp.di.viewModelModule
import com.xuanlocle.weatherapp.util.WeatherPreference
import com.xuanlocle.weatherapp.util.resources.Resources
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

val weatherPreference: WeatherPreference by lazy { WeatherApplication.prefs }

class WeatherApplication : MultiDexApplication(), KodeinAware {

    companion object {
        lateinit var instance: WeatherApplication
        lateinit var instanceResources: Resources
        lateinit var prefs: WeatherPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = WeatherPreference(applicationContext)
        instance = this
        instanceResources = Resources.apply {
            init(instance)
        }
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))
        importAll(modelModule, appModule, repositoryModule, viewModelModule)
    }

}