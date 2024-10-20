package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */


class MsCarouselLayoutManager(
    context: Context?,
    orientation: Int,
    reverseLayout: Boolean,
    private val shrinkAmount  : Float = 0.15f,
    private val shrinkDistance : Float = 0.9f


) :
    LinearLayoutManager(context, orientation, reverseLayout) {
    private var smoothScroller: SmoothScroller? = null

    var anchor: Int = 0

    private var scaleView = true

    init {
        if (smoothScroller == null) {
            smoothScroller = SmoothScroller(context)
        }
    }


    fun scaleView(scaleView: Boolean) {
        this.scaleView = scaleView
    }

    private fun isScaleView(): Boolean {
        return scaleView
    }


    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scrollVerticallyBy(0, recycler, state)
        scrollHorizontallyBy(0, recycler, state)
    }


    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            if (isScaleView()) {
                val midpoint = height / 2f
                val d0 = 0f
                val d1 = shrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - shrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale
                }
            }
            return scrolled
        } else {
            return 0
        }
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            if (isScaleView()) {
                val midpoint = width / 2f
                val d0 = 0f
                val d1 = shrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - shrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale
                }
            }
            return scrolled
        } else {
            return 0
        }
    }


    fun setScrollSpeed(scrollSpeed: Float) {
        smoothScroller!!.setScrollSpeed(scrollSpeed)
    }


    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        smoothScroller!!.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    inner class SmoothScroller(context: Context?, private var speed: Float = 150f) : LinearSmoothScroller(context) {


        fun setScrollSpeed(scrollSpeed: Float) {
            speed = scrollSpeed
        }


        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@MsCarouselLayoutManager.computeScrollVectorForPosition(targetPosition)
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
            return speed / displayMetrics.densityDpi;
        }
    }
}
