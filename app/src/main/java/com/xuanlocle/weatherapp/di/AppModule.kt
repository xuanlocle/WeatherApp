package com.xuanlocle.weatherapp.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xuanlocle.weatherapp.BuildConfig
import com.xuanlocle.weatherapp.data.remote.api.WeatherService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = Kodein.Module("appModule") {

    bind<OkHttpClient>() with singleton { createOkHttpClient() }

    bind<WeatherService>() with singleton {
        createWebService(okHttpClient = instance())
    }

}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String = BuildConfig.HOST,
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
            )
        )
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}


fun createOkHttpClient(): OkHttpClient {

    val client = OkHttpClient.Builder()
        .addInterceptor {
            val original = it.request()
            val url = original.url.newBuilder()
                .addQueryParameter("appId", BuildConfig.API_KEY)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            return@addInterceptor it.proceed(request)
        }

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        client.addInterceptor(httpLoggingInterceptor)
    }

    return client.connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .build()
}
