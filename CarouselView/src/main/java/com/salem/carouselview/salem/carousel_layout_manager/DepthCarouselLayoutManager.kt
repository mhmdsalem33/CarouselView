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


class DepthCarouselLayoutManager(
    context: Context,
    private val scaleFactor: Float = 0.3f,
    private val alphaFactor: Float = 0.5f
) : LinearLayoutManager(context, HORIZONTAL, false) {

    private var recyclerView: RecyclerView? = null
    private var isAutoScrolling = false

    override fun onAttachedToWindow(view: RecyclerView?) {
        super.onAttachedToWindow(view)
        recyclerView = view
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        if (!isAutoScrolling) {
            applyDepthEffect()  // Apply depth effects during scroll
        }
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyDepthEffect()  // Apply depth effects when layout completes
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == RecyclerView.SCROLL_STATE_IDLE && !isAutoScrolling) {
            // Center the nearest child when the scrolling stops
            centerItem()
        }
    }

    private fun applyDepthEffect() {
        val centerX = width / 2  // The center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Scale down items further from the center
            val scale = 1f - (scaleFactor * (distanceFromCenter / centerX.toFloat()))
            child.scaleX = scale
            child.scaleY = scale

            // Fade out items further from the center
            val alpha = 1f - (alphaFactor * (distanceFromCenter / centerX.toFloat()))
            child.alpha = alpha.coerceIn(0f, 1f)
        }
    }

    private fun centerItem() {
        val centerX = width / 2  // The center X position of the RecyclerView
        var minDistance = Int.MAX_VALUE
        var closestChild: View? = null

        // Find the child view closest to the center
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
}