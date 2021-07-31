package com.xuanlocle.weatherapp.data.model

data class TemperatureItem(
    val day: Float?,
    val min: Float?,
    val max: Float?,
    val night: Float?,
    val eve: Float?,
    val morn: Float?,
    val temperatureUnit: TemperatureUnitEnum?
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
