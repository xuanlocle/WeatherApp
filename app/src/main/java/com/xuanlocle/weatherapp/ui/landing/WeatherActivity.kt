package com.xuanlocle.weatherapp.ui.landing

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.ui.adapter.WeatherItemAdapter
import com.xuanlocle.weatherapp.ui.landing.dialog.FilterDialog
import com.xuanlocle.weatherapp.ui.landing.dialog.FilterDialogListener
import com.xuanlocle.weatherapp.util.WidgetUtils
import com.xuanlocle.weatherapp.util.resources.Resources
import kotlinx.android.synthetic.main.activity_weather.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class WeatherActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: WeatherViewModelFactory by instance()
    private lateinit var viewModel: WeatherViewModel
    private lateinit var rvAdapter: WeatherItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        init()
        initListener()
        initRecyclerView()
        initObserve()

        viewModel.getLatestWeatherEntity()
    }

    private fun initListener() {

        imvFilter.setOnClickListener {
            showFilterDialog()
        }

        btnSearch.setOnClickListener {
            if (edtSearch.text.isEmpty()) {
                edtSearch.error = Resources.getString(R.string.error_field)
                return@setOnClickListener
            }
            edtSearch.error = null
            viewModel.getLatestWeather(edtSearch.text.toString())
        }

        edtSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (edtSearch.text.isNotEmpty()) {
                    viewModel.getLatestWeather(edtSearch.text.toString())
                }
            }
            false
        }
    }

    private fun showFilterDialog() {
        FilterDialog.newInstance(object : FilterDialogListener {
            override fun onChangeFilter() {
                viewModel.getLatestWeather()
            }
        }).show(supportFragmentManager, FilterDialog::class.java.simpleName)
    }

    private fun initObserve() {
        viewModel.showError.observe(this) { errorResponse ->
            rvAdapter.showEmptyResult(errorResponse.cod ?: "0",
                errorResponse.message ?: "HTTP ERROR")
            tvCityName.text =
                String.format(Resources.getString(R.string.weather_search_city_title),
                    "Not found")
            return@observe
        }

        viewModel.weatherDetailsList.observe(this, { response ->
            response.list?.let {
                rvAdapter.updateData(response.list, response.temperatureUnitTemperature)
                tvCityName.text =
                    String.format(Resources.getString(R.string.weather_search_city_title),
                        response.city?.name ?: "")
            }
        })

        viewModel.showLoading.observe(this, {
            if (it) {
                tvCityName.text = Resources.getString(R.string.weather_search_city_title_loading)
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun initRecyclerView() {
        rvAdapter = WeatherItemAdapter(listOf())
        rvWeather.layoutManager = LinearLayoutManager(this)
        rvWeather.adapter = rvAdapter
    }

    private fun init() {
        viewModel = ViewModelProvider(this, factory).get(WeatherViewModel::class.java)
        viewModel.init()

    }

    private fun showLoading() {
        vLoading.visibility = View.VISIBLE
        WidgetUtils.hideSoftKeyboard(this, edtSearch)

    }

    private fun hideLoading() {
        vLoading.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::viewModel.isInitialized)
            viewModel.onDestroy()
    }

}