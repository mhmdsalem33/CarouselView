package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sin

/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */


class WaveCarouselLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val waveHeight: Float = 30f,  // Height of the wave effect
    private val waveFrequency: Float = 0.1f // Frequency of the wave
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private var smoothScroller: SmoothScroller? = null

    init {
        if (smoothScroller == null) {
            smoothScroller = SmoothScroller(context)
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        applyWaveEffect()
    }

    private fun applyWaveEffect() {
        val childCount = childCount
        if (childCount == 0) return

        val midpoint = height / 2f
        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val itemPosition = getPosition(child)
            val childCenterX = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
            val waveOffset = waveHeight * sin(itemPosition * waveFrequency) // Calculate wave effect
            child.translationY = waveOffset
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyWaveEffect() // Reapply wave effect after scrolling
        return scrolled
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        smoothScroller!!.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    inner class SmoothScroller(context: Context?, private var speed: Float = 150f) : LinearSmoothScroller(context) {
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@WaveCarouselLayoutManager.computeScrollVectorForPosition(targetPosition)
        }

        override fun calculateDtToFit(
            viewStart: Int,
            viewEnd: Int,
            boxStart: Int,
            boxEnd: Int,
            snapPreference: Int
        ): Int {
            return (boxStart + boxEnd) / 2 - (viewStart + viewEnd) / 2
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return speed / displayMetrics.densityDpi
        }
    }
}