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

class BasicCarouselLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {

    private val scaleFactor = 0.2f // Adjust this for scaling effect

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        scaleAndOffsetItems()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleAndOffsetItems()
    }

    private fun scaleAndOffsetItems() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = abs(centerX - childCenterX)

            val scale = 1 - (distanceFromCenter.toFloat() / width.toFloat())
            child.scaleX = 1f - (scaleFactor * scale)
            child.scaleY = 1f - (scaleFactor * scale)
            child.translationY = 0f // No vertical movement
        }
    }
}

