package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.max

/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */
class SkewCarouselLayoutManager(
    context: Context,
    private val maxSkew: Float = 0.5f // Controls the maximum skew effect
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applySkewEffect() // Apply skew, scaling, and alpha fading during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applySkewEffect() // Apply effects after layout is completed
    }

    private fun applySkewEffect() {
        val centerX = width / 2 // Get the center X of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Skew effect: based on the distance from the center
            val normalizedDistance = distanceFromCenter.toFloat() / width
            val skew = normalizedDistance * maxSkew

            // Set the skew effect on X-axis rotation
            child.pivotX = (child.width / 2).toFloat()
            child.pivotY = (child.height / 2).toFloat()
            child.rotationX = skew * 45f // Max 45-degree rotation for a nice skew effect

            // Scaling: Items closer to the center are larger, items further away are smaller
            val scaleFactor = max(0.85f, 1 - abs(normalizedDistance * 0.3f))
            child.scaleX = scaleFactor
            child.scaleY = scaleFactor

            // Alpha fading: Fade out items that are further from the center
            val alpha = max(0.5f, 1 - abs(normalizedDistance * 0.8f))
            child.alpha = alpha
        }
    }
}
