package com.xuanlocle.weatherapp.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

object Constants {
}

object WidgetUtils {

    fun hideSoftKeyboard(context: Context?, v: View) {
        if (context != null) {
            val imm =
                context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}