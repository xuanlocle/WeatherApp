package com.xuanlocle.weatherapp.ui.landing.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.xuanlocle.weatherapp.R
import com.xuanlocle.weatherapp.data.remote.request.UnitRequest
import com.xuanlocle.weatherapp.ui.base.BaseDialog
import com.xuanlocle.weatherapp.widget.MutableLiveDataSingle
import kotlinx.android.synthetic.main.dialog_filter_weather.*

class FilterDialog : BaseDialog() {

    companion object {
        fun newInstance(): FilterDialog {
            return FilterDialog()
        }
    }

    private val NUMPICKER_MIN_VALUE = 1
    private val NUMPICKER_DEFAULT_VALUE = 7
    private val NUMPICKER_MAX_VALUE = 17

    private var amountDayPicked: Int = 0
    private val unitTemperatureLiveData = MutableLiveDataSingle<UnitRequest>()


    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
        initObserver()

    }

    private fun initObserver() {
        unitTemperatureLiveData.observe(viewLifecycleOwner, { unitSelected ->
            tvUnitFahrenheit.isSelected = false
            tvUnitCelcius.isSelected = false
            tvUnitKelvin.isSelected = false
            when (unitSelected) {
                UnitRequest.METRIC -> {
                    tvUnitCelcius.isSelected = true
                }
                UnitRequest.IMPERIAL -> {
                    tvUnitFahrenheit.isSelected = true
                }

                UnitRequest.DEFAULT -> {
                    tvUnitKelvin.isSelected = true
                }
                else -> {
                }
            }

        })
    }

    private fun initListener() {
        vFilterDialog.setOnClickListener(null)

        vRootFilterDialog.setOnClickListener {
            this.dismiss()
        }

        npCount.setOnValueChangedListener { picker, oldVal, newVal ->
            amountDayPicked = newVal
        }

        tvUnitKelvin.setOnClickListener {
            unitTemperatureLiveData.postValue(UnitRequest.DEFAULT)
        }
        tvUnitCelcius.setOnClickListener {
            unitTemperatureLiveData.postValue(UnitRequest.METRIC)
        }
        tvUnitFahrenheit.setOnClickListener {
            unitTemperatureLiveData.postValue(UnitRequest.IMPERIAL)
        }

        tvSaveFilter.setOnClickListener {
            this.dismiss()
        }

    }

    private fun initData() {
        npCount.maxValue = NUMPICKER_MAX_VALUE;
        npCount.minValue = NUMPICKER_MIN_VALUE;
        npCount.value = NUMPICKER_DEFAULT_VALUE;

    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.dialog_filter_weather, null)
    }

}