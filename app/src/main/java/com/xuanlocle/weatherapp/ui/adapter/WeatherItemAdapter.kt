package com.xuanlocle.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.model.TemperatureUnitEnum
import com.xuanlocle.weatherapp.util.dates.DateTimeHelper
import com.xuanlocle.weatherapp.util.resources.Resources
import kotlinx.android.synthetic.main.weather_item.view.*
import kotlinx.android.synthetic.main.weather_item_empty.view.*

class WeatherItemAdapter(
    private var items: List<WeatherItem?>,
    private var temperatureUnitTemperature: TemperatureUnitEnum = TemperatureUnitEnum.DEFAULT,
) : RecyclerView.Adapter<WeatherItemAdapter.WeatherViewHolder>() {

    private var isShowEmptyView: Boolean = false
    private lateinit var errorMessage: String
    private lateinit var errorCode: String

    override fun getItemViewType(position: Int): Int {
        if (isShowEmptyView)
            return -1
        return 0;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        if (viewType == -1) {
            return WeatherViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_item_empty, parent, false))
        }

        return WeatherViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_item, parent, false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(rawHolder: WeatherViewHolder, position: Int) {
        when (getItemViewType(position)) {
            -1 -> {
                rawHolder.itemView.tvWeatherEmptyErrorCode.text = errorCode
                rawHolder.itemView.tvWeatherEmptyMessage.text = errorMessage
            }
            else -> {
                rawHolder.let { holder ->
                    val curWeatherItem: WeatherItem = items[position]!!
                    val day = DateTimeHelper.convertToFormat(curWeatherItem.dt ?: 0)
                    val averageTemp = curWeatherItem.temp?.getAvgTemp()
                    val temperatureUnit: String = getTemperatureString()

                    with(holder) {
                        itemView.tvWeatherDate.text =
                            String.format(Resources.getString(R.string.weather_item_date), day)
                        itemView.tvWeatherAvgTemp.text =
                            String.format(Resources.getString(R.string.weather_item_avg_temp),
                                averageTemp,
                                temperatureUnit)

                        itemView.tvWeatherPressure.text =
                            String.format(Resources.getString(R.string.weather_item_pressure),
                                curWeatherItem.pressure)

                        itemView.tvWeatherHumidity.text =
                            String.format(Resources.getString(R.string.weather_item_humidity),
                                curWeatherItem.humidity)

                        itemView.tvWeatherDescription.text =
                            String.format(Resources.getString(R.string.weather_item_description),
                                curWeatherItem.weather?.first()?.description ?: "Nothing")

                    }
                }
            }

        }

    }

    private fun getTemperatureString(): String {
        return Resources.getString(if (temperatureUnitTemperature.equals(TemperatureUnitEnum.METRIC))
            R.string.temperature_unit_celsius
        else if (temperatureUnitTemperature.equals(TemperatureUnitEnum.IMPERIAL))
            R.string.temperature_unit_fahrenheit
        else R.string.temperature_unit_kelvin
        )
    }

    fun showEmptyResult(errorCode: String, errorMessage: String) {
        this.items = listOf(null)
        this.isShowEmptyView = true
        this.errorCode = errorCode
        this.errorMessage = errorMessage
        notifyDataSetChanged();
    }

    fun updateData(list: List<WeatherItem>, temperatureUnitTemperature: TemperatureUnitEnum) {
        if (list.isEmpty()) {
            showEmptyResult("0", "Empty")
            return
        }

        this.isShowEmptyView = false
        this.items = list
        this.temperatureUnitTemperature = temperatureUnitTemperature
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}