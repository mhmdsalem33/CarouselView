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
    orientation: Int = RecyclerView.HORIZONTAL, // Updated to use RecyclerView.HORIZONTAL
    reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    private var scaleView = true
    private var shrinkAmount = 0.15f // Amount by which views shrink
    private var shrinkDistance = 0.9f // Distance from center before views start shrinking
    private var scrollSpeed = 1f // Speed factor for smooth scrolling

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        if (orientation == RecyclerView.HORIZONTAL) { // Updated to use RecyclerView.HORIZONTAL
            scaleAndOffsetItems() // Apply scaling
        }
        return scrolled
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollVerticallyBy(dy, recycler, state)
        if (orientation == RecyclerView.VERTICAL) { // Updated to use RecyclerView.VERTICAL
            scaleAndOffsetItems() // Apply scaling
        }
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems() // Apply scaling after layout
    }

    private fun scaleAndOffsetItems() {
        val center = if (orientation == RecyclerView.HORIZONTAL) width / 2 else height / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenter = if (orientation == RecyclerView.HORIZONTAL) {
                (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            } else {
                (getDecoratedTop(child) + getDecoratedBottom(child)) / 2
            }

            val distanceFromCenter = abs(center - childCenter).toFloat()
            val scale = 1 - min(shrinkAmount, distanceFromCenter / center * shrinkDistance)
            if (scaleView) {
                child.scaleX = scale
                child.scaleY = scale
            }
        }
    }

    fun setScaleView(scaleView: Boolean) {
        this.scaleView = scaleView
    }

    fun setScrollSpeed(scrollSpeed: Float) {
        this.scrollSpeed = scrollSpeed
    }

    fun setShrinkAmounts( amount : Float ){
       this.shrinkAmount = amount
    }

    fun setShrinkDistance(distance : Float) {
        this.shrinkDistance = distance
    }


    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        val smoothScroller = SmoothScroller(recyclerView.context, scrollSpeed)
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    inner class SmoothScroller(context: Context, private var speed: Float) : LinearSmoothScroller(context) {

        override fun calculateDtToFit(
            viewStart: Int, viewEnd: Int, boxStart: Int, boxEnd: Int, snapPreference: Int
        ): Int {
            return boxStart - viewStart // Aligns the view at the start
        }

        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return speed / displayMetrics.densityDpi
        }

        fun setScrollSpeed(scrollSpeed: Float) {
            this.speed = scrollSpeed
        }

        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@SmoothZoomLayoutManager.computeScrollVectorForPosition(targetPosition)
        }
    }
}