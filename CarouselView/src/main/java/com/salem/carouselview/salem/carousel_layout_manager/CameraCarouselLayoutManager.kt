package com.salem.carouselview.salem.carousel_layout_manager


import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.max


class CameraCarouselLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val scaleFactor: Float = 0.5f,
    private val translationFactor: Float = 100f // Translation distance for items
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private var smoothScroller: SmoothScroller? = null

    init {
        if (smoothScroller == null) {
            smoothScroller = SmoothScroller(context)
        }
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        applyCameraEffect()
    }

    private fun applyCameraEffect() {
        val childCount = childCount
        if (childCount == 0) return

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenter = (getDecoratedBottom(child) + getDecoratedTop(child)) / 2f
            val midpoint = height / 2f
            val distanceFromCenter = abs(midpoint - childCenter)

            // Scale and translate the view based on its distance from the center
            val scale = max(0f, 1 - (distanceFromCenter / (midpoint * scaleFactor)))
            val translation = (distanceFromCenter / midpoint) * translationFactor

            // Apply scale and translation
            child.scaleX = scale
            child.scaleY = scale
            child.translationY = translation
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyCameraEffect() // Reapply camera effect after scrolling
        return scrolled
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        smoothScroller!!.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    inner class SmoothScroller(context: Context?, private var speed: Float = 150f) : LinearSmoothScroller(context) {
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@CameraCarouselLayoutManager.computeScrollVectorForPosition(targetPosition)
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