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

class DepthCarouselLayoutManagerPlus(context: Context, private val depthFactor: Float = 0.5f) :
    LinearLayoutManager(context, HORIZONTAL, false) {


    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyDepthEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyDepthEffect()
    }

    private fun applyDepthEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Apply depth effect: scale down items as they move away from the center
            val scale = 1f - (distanceFromCenter.toFloat() / width) * depthFactor
            child.scaleX = scale
            child.scaleY = scale
            child.translationZ = -distanceFromCenter.toFloat() * depthFactor
        }
    }
}
