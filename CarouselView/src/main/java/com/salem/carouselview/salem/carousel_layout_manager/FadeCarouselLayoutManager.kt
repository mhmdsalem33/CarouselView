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

class FadeCarouselLayoutManager(
    context: Context,
    private val scaleFactor: Float = 0.2f, // Control the scale effect
    private val fadeFactor: Float = 0.5f   // Control the fading effect
) : LinearLayoutManager(context, HORIZONTAL, false) {




    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndFadeItems()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndFadeItems()
    }

    private fun scaleAndFadeItems() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            // Calculate scale
            val scale = 1 - (distanceFromCenter.toFloat() / width.toFloat())
            child.scaleX = 1f - (scaleFactor * scale)
            child.scaleY = 1f - (scaleFactor * scale)

            // Calculate alpha for fading effect
            val alpha = 1 - (fadeFactor * (distanceFromCenter / (width / 2)))
            child.alpha = alpha.coerceIn(0f, 1f) // Ensure alpha is between 0 and 1
            child.translationY = 0f
        }
    }
}
