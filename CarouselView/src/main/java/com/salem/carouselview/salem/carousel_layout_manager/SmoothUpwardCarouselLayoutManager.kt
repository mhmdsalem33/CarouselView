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
    private val moveUpFactor : Float = 50f // Controls how much items move up
) : LinearLayoutManager(context, HORIZONTAL, false) {


    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applySmoothUpEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applySmoothUpEffect()
    }

    private fun applySmoothUpEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Smooth upward movement based on distance from the center
            val moveUp = (distanceFromCenter.toFloat() / width) * moveUpFactor
            child.translationY = -moveUp // Move upwards
            child.rotationY = 0f // No tilt effect
        }
    }
}
