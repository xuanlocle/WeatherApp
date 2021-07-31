package com.xuanlocle.weatherapp.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.TypefaceCompat
import com.xuanlocle.weatherapp.R

@SuppressLint("AppCompatCustomView")
class TextViewEx : AppCompatTextView {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        var fontType = -1
        var fontSize = -1
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TypefaceView)
            fontType = a.getInt(R.styleable.TypefaceView_fontType, -1)
            fontSize = a.getInt(R.styleable.TypefaceView_fontSize, -1)
            a.recycle()
        }
        typeface = TypefaceCompat.create(getContext(), typeface,
            when (fontType) {
                3 -> {
                    Typeface.ITALIC
                }
                2 -> {
                    Typeface.BOLD
                }
                else -> {
                    Typeface.NORMAL
                }
            });

        setTextSize(textSizeUnit, when (fontSize) {
            0 -> 12f
            2 -> 20f
            3 -> 30f
            else -> 14f
        })

        includeFontPadding = false
    }

}