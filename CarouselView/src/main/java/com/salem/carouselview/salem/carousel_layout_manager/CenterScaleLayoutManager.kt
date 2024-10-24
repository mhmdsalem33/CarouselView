package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */


class CenterScaleLayoutManager(context: Context, private val scaleFactor: Float = 0.15f) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndOffsetItems() // Apply scaling during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems() // Apply scaling after layout
    }

    private fun scaleAndOffsetItems() {
        val centerX = width / 2 // Center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Calculate scale: Items closer to center are scaled up
            val scale = 1 - (distanceFromCenter.toFloat() / width.toFloat())
            child.scaleX = 1f - (scaleFactor * scale)
            child.scaleY = 1f - (scaleFactor * scale)

            // Set vertical translation to zero to avoid moving up and down
            child.translationY = 0f
        }
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        // Get the target view for the desired position
        val targetView = findViewByPosition(position)

        if (targetView != null) {
            val centerX = recyclerView.width / 2
            val childCenterX = (getDecoratedLeft(targetView) + getDecoratedRight(targetView)) / 2
            val distanceToScroll = childCenterX - centerX

            // Smooth scroll the calculated distance to center the item
            recyclerView.smoothScrollBy(distanceToScroll, 0)
        } else {
            // If the target view isn't laid out yet, fall back to default smooth scrolling
            super.smoothScrollToPosition(recyclerView, state, position)
        }
    }
}

