package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class FadeCarouselLayoutManager(
    context: Context,
    private val scaleFactor: Float = 0.2f,  // Control the scale effect
    private val fadeFactor: Float = 0.5f    // Control the fading effect
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyScaleAndFadeEffect()  // Apply scaling and fading effects during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyScaleAndFadeEffect()  // Apply scaling and fading effects after layout completion
    }

    private fun applyScaleAndFadeEffect() {
        val centerX = width / 2  // Get the center X position of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Scale effect based on distance from center
            val scale = 1f - (distanceFromCenter.toFloat() / width) * scaleFactor
            child.scaleX = scale
            child.scaleY = scale

            // Fade effect based on distance from center
            val alpha = 1f - (fadeFactor * (distanceFromCenter / (width / 2f)))
            child.alpha = alpha.coerceIn(0f, 1f)
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
            // Fallback to default smooth scrolling if the target view isn't available
            super.smoothScrollToPosition(recyclerView, state, position)
        }
    }
}
