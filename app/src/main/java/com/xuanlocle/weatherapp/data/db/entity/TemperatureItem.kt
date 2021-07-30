package com.xuanlocle.weatherapp.data.db.entity

import com.xuanlocle.weatherapp.data.remote.request.UnitRequest

data class TemperatureItem(
    val day: Float?,
    val min: Float?,
    val max: Float?,
    val night: Float?,
    val eve: Float?,
    val morn: Float?,
    val unit: UnitRequest?
) {
    fun getAvgTemp(): Float {
        var sum = 0f
        var count = 0
        listOf(day, min, max, night, eve, morn).forEach { element ->
            element?.let {
                if (it > 0f) {
                    sum += it
                    count += 1
                }
            }

        }
        if (count == 0)
            return 0f
        else
            return sum.div(count)
    }

}
