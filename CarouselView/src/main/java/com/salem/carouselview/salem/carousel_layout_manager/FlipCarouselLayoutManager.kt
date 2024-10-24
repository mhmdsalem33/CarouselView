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
class FlipCarouselLayoutManager(context: Context, private val maxFlip: Float = 30f) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    /**
     * You can set your maxFlip: 10f, 20f, 30f, 90f, 180f, or as you need.
     * This version adds scaling and fading for a more elegant effect.
     */

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyFlipEffect() // Apply flipping, scaling, and fading during scroll
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyFlipEffect() // Apply flipping, scaling, and fading after layout completion
    }

    private fun applyFlipEffect() {
        val centerX = width / 2  // Get the center of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Flip items based on distance from center, with maxFlip as the maximum rotation
            val normalizedDistance = distanceFromCenter.toFloat() / width
            val flip = max(-maxFlip, minOf(normalizedDistance * maxFlip, maxFlip))

            // Apply flip effect
            child.pivotX = (child.width / 2).toFloat()
            child.pivotY = (child.height / 2).toFloat()
            child.rotationX = flip

            // Apply scale effect (items closer to the center are larger)
            val scaleFactor = max(0.8f, 1 - abs(normalizedDistance * 0.3f))
            child.scaleX = scaleFactor
            child.scaleY = scaleFactor

            // Apply alpha fading (items further from the center become more transparent)
            val alpha = max(0.3f, 1 - abs(normalizedDistance))
            child.alpha = alpha
        }
    }
}