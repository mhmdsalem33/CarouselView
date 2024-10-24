package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class SmoothUpwardCarouselLayoutManager(
    context: Context,
    private val moveUpFactor: Float = 50f,    // Controls upward movement
    private val scaleDownFactor: Float = 0.2f, // Scaling factor for items
    private val visibleItemCount: Int = 3     // Number of visible items (center + neighbors)
) : LinearLayoutManager(context, HORIZONTAL, false) {

    private var recyclerViewPadding: Int = 0
    private var itemSpacing: Int = 20 // Default spacing between items

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyUpwardMovementEffect()  // Apply scaling and upward movement during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyUpwardMovementEffect()  // Apply effect after layout completion
    }

    private fun applyUpwardMovementEffect() {
        val centerX = width / 2  // Get the center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // 1. Apply upward movement based on distance from center
            val moveUp = (distanceFromCenter.toFloat() / width) * moveUpFactor
            child.translationY = -moveUp  // Move items upwards

            // 2. Apply scaling: Closer items are bigger, further items are smaller
            val scaleFactor = 1f - (distanceFromCenter.toFloat() / width * scaleDownFactor)
            child.scaleX = scaleFactor
            child.scaleY = scaleFactor

            // 3. Optional: Adjust the transparency based on distance from the center
            val alphaFactor = 1f - (distanceFromCenter.toFloat() / width * 0.5f)
            child.alpha = alphaFactor.coerceIn(0.3f, 1f)
        }
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        val targetView = findViewByPosition(position)

        if (targetView != null) {
            val centerX = recyclerView.width / 2
            val childCenterX = (getDecoratedLeft(targetView) + getDecoratedRight(targetView)) / 2
            val distanceToScroll = childCenterX - centerX

            // Smooth scroll the calculated distance to center the item
            recyclerView.smoothScrollBy(distanceToScroll, 0)
        } else {
            // Fallback to default smooth scrolling if the target view isn't available
            super.smoothScrollToPosition(recyclerView, state, position)
        }
    }

    // Set padding dynamically based on the number of visible items
    fun setRecyclerViewPadding(recyclerView: RecyclerView) {
        recyclerViewPadding = recyclerView.width / (visibleItemCount * 2)
        recyclerView.setPadding(recyclerViewPadding, 0, recyclerViewPadding, 0)
        recyclerView.clipToPadding = false  // Ensure items outside padding are visible
    }

    // Customize the space between items
    fun setItemSpacing(spacing: Int) {
        this.itemSpacing = spacing
    }

    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        // Allow for extra space for smooth scrolling, so items load offscreen
        extraLayoutSpace[0] = width / 2
        extraLayoutSpace[1] = width / 2
    }
}

