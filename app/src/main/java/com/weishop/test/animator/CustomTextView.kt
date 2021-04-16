package com.weishop.test.animator

import android.content.Context
import android.util.AttributeSet
import com.weishop.test.util.LogUtils

class CustomTextView : androidx.appcompat.widget.AppCompatTextView {

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr)


    override fun setTranslationX(translationX: Float) {
        LogUtils.d("setTranslationX ${translationX}")
        super.setTranslationX(translationX)

    }

    override fun getTranslationX(): Float {
        val translationX = super.getTranslationX()
        LogUtils.d("getTranslationX ${translationX}")
        return translationX
    }
}