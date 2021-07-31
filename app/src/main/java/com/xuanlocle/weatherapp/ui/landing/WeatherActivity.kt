package com.xuanlocle.weatherapp.ui.landing

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.ui.adapter.WeatherItemAdapter
import com.xuanlocle.weatherapp.ui.landing.dialog.FilterDialog
import com.xuanlocle.weatherapp.ui.landing.dialog.FilterDialogListener
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
        viewModel.getLatestWeather()
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

            viewModel.getLatestWeather(edtSearch.text.toString())
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
        viewModel.weatherDetailsList.observe(this, { response ->
            if (response?.list != null && response.list.isNotEmpty()) {
                rvAdapter.items = response.list
                rvAdapter.unitTemperature = response.unitTemperature
                rvAdapter.notifyDataSetChanged()
            }
        })

        viewModel.showLoading.observe(this, {
            if (it) {
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