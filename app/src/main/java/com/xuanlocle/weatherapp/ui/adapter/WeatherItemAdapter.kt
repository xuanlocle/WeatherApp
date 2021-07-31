package com.xuanlocle.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.data.model.WeatherItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.util.dates.DateTimeHelper
import com.xuanlocle.weatherapp.util.resources.Resources
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherItemAdapter(
    private var items: List<WeatherItem?>,
    private var unitTemperature: UnitRequest = UnitRequest.DEFAULT,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isShowEmptyView: Boolean = false

    override fun getItemViewType(position: Int): Int {
        if (isShowEmptyView)
            return -1
        return 0;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

    override fun onBindViewHolder(rawHolder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            -1 -> {

            }
            else -> {
                (rawHolder as WeatherViewHolder).let { holder ->
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
        return Resources.getString(if (unitTemperature.equals(UnitRequest.METRIC))
            R.string.temperature_unit_celsius
        else if (unitTemperature.equals(UnitRequest.IMPERIAL))
            R.string.temperature_unit_fahrenheit
        else R.string.temperature_unit_kelvin
        )
    }

    fun showEmptyResult() {
        this.items = listOf(null)
        this.isShowEmptyView = true
        notifyDataSetChanged();
    }

    fun updateData(list: List<WeatherItem>, unitTemperature: UnitRequest) {
        if (list.isEmpty()) {
            showEmptyResult()
            return
        }

        this.isShowEmptyView = false
        this.items = list
        this.unitTemperature = unitTemperature
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}