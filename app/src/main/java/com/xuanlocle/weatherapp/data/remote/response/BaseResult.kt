package com.xuanlocle.weatherapp.data.remote.response

sealed class BaseResult<out T: Any> {
    class Success<T : Any>(val data: T?) : BaseResult<T>()
    class Error(val error: ErrorResponse) : BaseResult<Nothing>()
}