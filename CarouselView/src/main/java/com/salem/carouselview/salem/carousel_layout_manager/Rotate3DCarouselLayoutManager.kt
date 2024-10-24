package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.max

/**
 *  Mohamed Salem
 *  24/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */


class Rotate3DCarouselLayoutManager(
    context: Context,
    private val maxRotation: Float = 30f     // Max degree of rotation
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        apply3DRotationEffect() // Apply 3D rotation, scale, and fade effects
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        apply3DRotationEffect() // Apply effects after layout completion
    }

    private fun apply3DRotationEffect() {
        val centerX = width / 2  // Center of the RecyclerView

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Calculate rotation based on the distance from the center
            val rotation = (distanceFromCenter.toFloat() / width) * maxRotation

            // Set dynamic pivotX (left edge if moving to the right, right edge if moving left)
            child.pivotX = if (distanceFromCenter > 0) 0f else child.width.toFloat()
            child.pivotY = (child.height / 2).toFloat()
            child.rotationY = rotation

            // Scale effect to simulate depth (closer items are larger)
            val scaleFactor = max(0.85f, 1 - abs(distanceFromCenter.toFloat() / width * 0.3f))
            child.scaleX = scaleFactor
            child.scaleY = scaleFactor

            // Fade out items as they move away from the center
            val alpha = max(0.4f, 1 - abs(distanceFromCenter.toFloat() / width))
            child.alpha = alpha
        }
    }
}
