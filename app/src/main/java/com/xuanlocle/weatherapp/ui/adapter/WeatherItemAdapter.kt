package com.xuanlocle.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.data.db.entity.WeatherItem
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.util.dates.DateTimeHelper
import com.xuanlocle.weatherapp.util.resources.Resources
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherItemAdapter(
    var items: List<WeatherItem>,
    var unitTemperature: UnitRequest = UnitRequest.DEFAULT,
) : RecyclerView.Adapter<WeatherItemAdapter.WeatherViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val curWeatherItem = items[position]
        val day = DateTimeHelper.convertToFormat(curWeatherItem.dt)
        val averageTemp = curWeatherItem.temp.getAvgTemp()
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
                    curWeatherItem.weather.first().description ?: "Nothing")

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

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}