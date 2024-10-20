package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class ZoomSnapLayoutManager(
    context: Context,
    private val scaleFactor: Float = 0.3f, // Increase this for more pronounced zoom effect
    private val minScale: Float = 0.85f   // Minimum scale for farthest items
) : LinearLayoutManager(context, HORIZONTAL, false) {

    // Factor to control how much scaling happens, higher value means bigger zoom

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndOffsetItems() // Apply zoom scaling during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems() // Apply zoom scaling after layout
    }

    private fun scaleAndOffsetItems() {
        val centerX = width / 2 // Center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Calculate scale: Items closer to center are scaled up, others are scaled down
            val scale =
                minScale + (1 - distanceFromCenter.toFloat() / width.toFloat()) * (1 - minScale)
            child.scaleX = scale + (scaleFactor * scale)
            child.scaleY = scale + (scaleFactor * scale)

            // Set vertical translation to zero to avoid moving up and down
            child.translationY = 0f
        }
    }

    /**
     * Calculate which view is closest to the center and return its position.
     * This is the item to "snap" to the center.
     */
    private fun calculateSnapViewPosition(): Int {
        val parentAnchor = width / 2

        val lastVisibleItemPosition = findLastVisibleItemPosition()
        val firstVisibleItemPosition = findFirstVisibleItemPosition()

        if (firstVisibleItemPosition > -1) {
            var currentViewClosestToAnchor: View? = findViewByPosition(firstVisibleItemPosition)
            var currentViewClosestToAnchorDistance =
                parentAnchor - getViewAnchor(currentViewClosestToAnchor)
            var currentViewClosestToAnchorPosition = firstVisibleItemPosition

            for (i in firstVisibleItemPosition + 1..lastVisibleItemPosition) {
                val view = findViewByPosition(i) ?: continue
                val distanceToCenter = parentAnchor - getViewAnchor(view)
                if (abs(distanceToCenter) < abs(currentViewClosestToAnchorDistance)) {
                    currentViewClosestToAnchorPosition = i
                    currentViewClosestToAnchorDistance = distanceToCenter
                }
            }
            return currentViewClosestToAnchorPosition
        } else {
            return -1
        }
    }

    private fun getViewAnchor(view: View?): Int {
        return if (view == null) 0 else (getDecoratedLeft(view) + getDecoratedRight(view)) / 2
    }
}

