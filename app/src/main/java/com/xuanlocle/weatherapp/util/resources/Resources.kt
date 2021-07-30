package com.xuanlocle.weatherapp.util.resources

import android.content.Context
import android.content.res.Resources

object Resources {

    var context: Context? = null

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    val resources: Resources?
        get() = context!!.resources

    fun getString(id: Int): String {
        return resources!!.getString(id)
    }

}

