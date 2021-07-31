package com.xuanlocle.weatherapp.util.error

import com.google.gson.Gson
import com.xuanlocle.weatherapp.data.remote.response.ErrorResponse
import retrofit2.HttpException


object HttpUtils {

    fun parseError(ex: Throwable): ErrorResponse {
        try {
            val body = (ex as HttpException).response()?.errorBody()?.string()

            val convertedObject: ErrorResponse = Gson().fromJson(body, ErrorResponse::class.java)
            return convertedObject
        } catch (ex: Throwable) {
            return ErrorResponse(message = "Http Error")
        }
    }

}