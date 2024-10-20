package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import android.view.View

/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */
class CenterScaleLayoutManager(context: Context ,   private val scaleFactor :Float  = 0.15f) : LinearLayoutManager(context, HORIZONTAL, false) {



    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndOffsetItems() // Apply scaling during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems() // Apply scaling after layout
        calculateSnapViewPosition()
    }

    private fun scaleAndOffsetItems() {
        val centerX = width / 2 // Center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Calculate scale: Items closer to center are scaled up
            val scale = 1 - (distanceFromCenter.toFloat() / width.toFloat())
            child.scaleX = 1f - (scaleFactor * (scale))
            child.scaleY = 1f - (scaleFactor * (scale))

            // Set vertical translation to zero to avoid moving up and down
            child.translationY = 0f
        }
    }

    /**
     * Calculate which view is closest to the center and return its position.
     * This is the item to "snap" to the center.
     */
    private fun calculateSnapViewPosition(): Int {
        // Center X position of the RecyclerView (this acts as the parent anchor)
        val parentAnchor = width / 2

        // Get the range of visible item positions
        val lastVisibleItemPosition = findLastVisibleItemPosition()
        val firstVisibleItemPosition = findFirstVisibleItemPosition()

        // Ensure that there are visible items
        if (firstVisibleItemPosition > -1) {
            // Initialize variables to track the closest view to the center
            var currentViewClosestToAnchor: View? = findViewByPosition(firstVisibleItemPosition)
            var currentViewClosestToAnchorDistance = parentAnchor - getViewAnchor(currentViewClosestToAnchor)
            var currentViewClosestToAnchorPosition = firstVisibleItemPosition

            // Loop through the visible items to find the one closest to the center
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
            // No visible items found
            return -1
        }
    }

    /**
     * Get the anchor (center) of a view in the RecyclerView.
     * This is calculated as the midpoint between the left and right edges of the view.
     */
    private fun getViewAnchor(view: View?): Int {
        return if (view == null) 0 else (getDecoratedLeft(view) + getDecoratedRight(view)) / 2
    }
}
