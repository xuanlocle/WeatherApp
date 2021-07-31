package com.xuanlocle.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import com.xuanlocle.weatherapp.data.remote.response.ErrorResponse
import com.xuanlocle.weatherapp.widget.MutableLiveDataSingle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    private lateinit var job: Job
    protected lateinit var uiScope: CoroutineScope
    protected lateinit var ioContext: CoroutineContext

    val showError = MutableLiveDataSingle<ErrorResponse>()

    fun init() {
        onCreate()
    }

    protected open fun onCreate() {
        job = Job()
        uiScope = CoroutineScope(Dispatchers.Main + job)
        ioContext = Dispatchers.IO + job

    }

    fun onDestroy() {
        uiScope.cancel()
        ioContext.cancel()
    }

    open fun showLoading(){

    }

    open fun hideLoading() {
    }
}