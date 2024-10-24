package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WaveLayoutManager(
    context: Context,
    private val amplitude: Float = 50f,  // Controls wave height
    private val frequency: Float = 2f    // Controls wave frequency
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyWaveEffect()
        return scrolled
    }

    private fun applyWaveEffect() {
        val parentWidth = width

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = childCenterX.toFloat() / parentWidth

            // Apply sinusoidal translation for wave effect
            child.translationY = amplitude * kotlin.math.sin(frequency * distanceFromCenter * kotlin.math.PI.toFloat())
        }
    }
}
