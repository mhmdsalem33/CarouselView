package com.salem.carouselview.extentions

import android.view.View


object OnSingleClick {
    inline fun View.onSingleClick(
        delayMillis: Long = 2000L,
        crossinline action: (v: View) -> Unit
    ) {
        setOnClickListener { v ->
            isEnabled = false
            action(v)
            postDelayed({ isEnabled = true }, delayMillis)
        }
    }
}