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
        applySkewEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applySkewEffect()
    }

    private fun applySkewEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Skew the items based on distance from center
            val skew = (distanceFromCenter.toFloat() / width) * maxSkew
            child.scaleX = 1f - abs(skew)
            child.rotationX = skew * 45 // Skewing the items along X-axis
        }
    }
}
