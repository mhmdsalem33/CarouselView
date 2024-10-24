package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class DepthCarouselLayoutManagerPlus(context: Context, private val depthFactor: Float = 0.5f) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    private var recyclerView: RecyclerView? = null
    private var isAutoScrolling = false

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
        recyclerView = view
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        if (!isAutoScrolling) {
            applyDepthEffect()  // Apply depth effect during scroll
        }
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyDepthEffect()  // Apply depth effect after layout completion
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE && !isAutoScrolling) {
            // Center the nearest child when scrolling stops
            centerItem()
        }
    }

    private fun applyDepthEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Apply depth effect: scale and translateZ items based on distance from center
            val scale = 1f - (distanceFromCenter.toFloat() / width) * depthFactor
            child.scaleX = scale
            child.scaleY = scale
            child.translationZ = -distanceFromCenter.toFloat() * depthFactor
        }
    }

    private fun centerItem() {
        val centerX = width / 2
        var minDistance = Int.MAX_VALUE
        var closestChild: View? = null

        // Find the child closest to the center
        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            if (distanceFromCenter < minDistance) {
                minDistance = distanceFromCenter
                closestChild = child
            }
        }

        // Center the closest child
        closestChild?.let {
            val childCenterX = (getDecoratedLeft(it) + getDecoratedRight(it)) / 2
            val distanceToCenter = childCenterX - centerX

            if (distanceToCenter != 0) {
                isAutoScrolling = true
                recyclerView?.smoothScrollBy(distanceToCenter, 0)  // Smooth scroll to center
                isAutoScrolling = false
            }
        }
    }

    /**
     * Centers the clicked item in the carousel.
     */
    fun centerItemOnClick(position: Int) {
        val view = findViewByPosition(position)
        if (view != null) {
            val centerX = width / 2
            val childCenterX = (getDecoratedLeft(view) + getDecoratedRight(view)) / 2
            val distanceToCenter = childCenterX - centerX

            if (distanceToCenter != 0) {
                isAutoScrolling = true
                recyclerView?.smoothScrollBy(distanceToCenter, 0)  // Smooth scroll to center the clicked item
                isAutoScrolling = false
            }
        }
    }
}