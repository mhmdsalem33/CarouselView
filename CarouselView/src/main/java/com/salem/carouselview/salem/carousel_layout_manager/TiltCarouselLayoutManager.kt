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
    private var tiltFactor : Float = 15f // Controls how much items tilt

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyTiltEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyTiltEffect()
    }

    private fun applyTiltEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Apply tilting effect based on distance from center
            child.rotationY = (distanceFromCenter.toFloat() / width) * tiltFactor
            child.translationY = 0f
        }
    }

    fun setTiltFactor( tiltValue : Float ){
        this.tiltFactor = tiltValue
    }
}
