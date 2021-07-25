package com.weishop.test.util

import android.R
import android.content.res.ColorStateList
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.DrawableCompat

object switchutils {
    fun setSwitchColor(v: SwitchCompat) {

        // thumb color
        val thumbColor = -0x99cd

        // trackColor
        val trackColor = -0xe0e0f

        // set the thumb color
        DrawableCompat.setTintList(
            v.thumbDrawable, ColorStateList(
                arrayOf(intArrayOf(R.attr.state_checked), intArrayOf()), intArrayOf(
                    thumbColor,
                    trackColor
                )
            )
        )

        // set the track color
        DrawableCompat.setTintList(
            v.trackDrawable, ColorStateList(
                arrayOf(intArrayOf(R.attr.state_checked), intArrayOf()), intArrayOf(
                    0x4DFF6633,
                    0x4d2f2f2f
                )
            )
        )
    }
}