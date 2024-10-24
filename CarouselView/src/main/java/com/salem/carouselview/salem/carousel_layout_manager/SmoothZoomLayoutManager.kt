package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.min

/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class SmoothZoomLayoutManager(
    context: Context,
    orientation: Int = RecyclerView.HORIZONTAL,
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private var scaleView = true
    private var shrinkAmount = 0.15f // Amount by which views shrink
    private var shrinkDistance = 0.9f // Distance from center before views start shrinking
    private var scrollSpeed = 1f // Speed factor for smooth scrolling

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        if (orientation == RecyclerView.HORIZONTAL) {
            scaleAndOffsetItems() // Apply scaling on horizontal scroll
        }
        return scrolled
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollVerticallyBy(dy, recycler, state)
        if (orientation == RecyclerView.VERTICAL) {
            scaleAndOffsetItems() // Apply scaling on vertical scroll
        }
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems() // Apply scaling after layout completion
    }

    private fun scaleAndOffsetItems() {
        val center = if (orientation == RecyclerView.HORIZONTAL) width / 2 else height / 2
        val maxScale = 1f
        val minScale = 1f - shrinkAmount

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenter = if (orientation == RecyclerView.HORIZONTAL) {
                (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            } else {
                (getDecoratedTop(child) + getDecoratedBottom(child)) / 2
            }

            val distanceFromCenter = abs(center - childCenter).toFloat()
            val distanceRatio = distanceFromCenter / center
            val scale = maxScale - min(shrinkAmount, distanceRatio * shrinkDistance)

            // Apply scaling if enabled
            if (scaleView) {
                child.scaleX = scale.coerceIn(minScale, maxScale)
                child.scaleY = scale.coerceIn(minScale, maxScale)
            }

            // Optional: Center items with slight translation based on scaling (optional)
            child.translationX = (center - childCenter) * 0.1f * scale
        }
    }

    // Allow external configuration of scaling and speed
    fun setScaleView(enableScaling: Boolean) {
        this.scaleView = enableScaling
    }

    fun setScrollSpeed(newScrollSpeed: Float) {
        this.scrollSpeed = newScrollSpeed
    }

    fun setShrinkAmount(amount: Float) {
        this.shrinkAmount = amount
    }

    fun setShrinkDistance(distance: Float) {
        this.shrinkDistance = distance
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        // Get the target view for the desired position
        val targetView = findViewByPosition(position)

        if (targetView != null) {
            val center = if (orientation == RecyclerView.HORIZONTAL) recyclerView.width / 2 else recyclerView.height / 2
            val childCenter = if (orientation == RecyclerView.HORIZONTAL) {
                (getDecoratedLeft(targetView) + getDecoratedRight(targetView)) / 2
            } else {
                (getDecoratedTop(targetView) + getDecoratedBottom(targetView)) / 2
            }

            val distanceToScroll = childCenter - center

            // Smooth scroll the calculated distance to center the item
            recyclerView.smoothScrollBy(if (orientation == RecyclerView.HORIZONTAL) distanceToScroll else 0, if (orientation == RecyclerView.VERTICAL) distanceToScroll else 0)
        } else {
            // Fallback to default smooth scrolling
            super.smoothScrollToPosition(recyclerView, state, position)
        }
    }

    inner class SmoothScroller(context: Context, private var speed: Float) : LinearSmoothScroller(context) {

        override fun calculateDtToFit(
            viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int
        ): Int {
            // Align the view in the center instead of the start
            val viewCenter = (viewStart + viewEnd) / 2
            val boxCenter = (boxStart + boxEnd) / 2
            return viewCenter - boxCenter
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            // Adjust speed based on screen density and user-configured scrollSpeed
            return speed / displayMetrics.densityDpi
        }

        fun setScrollSpeed(newScrollSpeed: Float) {
            this.speed = newScrollSpeed
        }

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@SmoothZoomLayoutManager.computeScrollVectorForPosition(targetPosition)
        }
    }
}
