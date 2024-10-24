package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class TiltCarouselLayoutManager(
    context: Context,
) : LinearLayoutManager(context, HORIZONTAL, false) {

    private var tiltFactor: Float = 15f // Controls how much items tilt

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyTiltEffect()  // Apply tilt effect as items scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyTiltEffect()  // Apply tilt effect after layout is completed
    }

    private fun applyTiltEffect() {
        val centerX = width / 2  // Get the center X of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Apply tilt effect based on the distance from the center
            val tilt = (distanceFromCenter.toFloat() / width) * tiltFactor
            child.rotationY = tilt
            child.translationY = 0f  // Reset any vertical translation
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

    // Setter for adjusting the tilt factor
    fun setTiltFactor(tiltValue: Float) {
        this.tiltFactor = tiltValue
    }

    // Optional: Add extra layout space to ensure smooth scrolling of offscreen items
    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        extraLayoutSpace[0] = width / 2  // Preload space before the first visible item
        extraLayoutSpace[1] = width / 2  // Preload space after the last visible item
    }
}
