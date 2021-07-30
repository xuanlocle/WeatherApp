package com.xuanlocle.weatherapp

import androidx.multidex.MultiDexApplication
import com.xuanlocle.weatherapp.di.appModule
import com.xuanlocle.weatherapp.di.repositoryModule
import com.xuanlocle.weatherapp.di.viewModelModule
import com.xuanlocle.weatherapp.util.resources.Resources
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class WeatherApplication : MultiDexApplication(), KodeinAware {

    companion object {
        lateinit var instance: WeatherApplication
        lateinit var instanceResources: Resources
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        instanceResources = Resources.apply {
            init(instance)
        }
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))
        importAll(appModule, repositoryModule, viewModelModule)
    }

}