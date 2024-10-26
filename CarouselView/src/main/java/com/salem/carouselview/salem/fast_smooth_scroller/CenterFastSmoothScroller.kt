package com.salem.carouselview.salem.fast_smooth_scroller

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class CenterFastSmoothScroller(context: Context , private val scrollSpeed : Float) : LinearSmoothScroller(context) {

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        // Adjust this value for speed: lower values mean faster scrolling
        // 0.1f is fast.
        // 0.05f is very fast.
        // 0.01f  // Extremely fast
        return scrollSpeed
    }

    override fun calculateDtToFit(
        viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int
    ): Int {
        // Calculate the distance required to align the item to the center
        val boxCenter = (boxStart + boxEnd) / 2
        val viewCenter = (viewStart + viewEnd) / 2
        return boxCenter - viewCenter
    }
}
