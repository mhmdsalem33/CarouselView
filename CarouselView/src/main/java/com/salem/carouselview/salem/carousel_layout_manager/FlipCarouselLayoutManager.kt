package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *  Mohamed Salem
 *  19/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */
class FlipCarouselLayoutManager(context: Context, private val maxFlip: Float = 30f) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    /**
     *  you can set your maxFlip   10f , 20f , 30f , 90f , 180f , and as you need
     **/

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        applyFlipEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        applyFlipEffect()
    }

    private fun applyFlipEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Flip items vertically as they move away from the center
            val flip = (distanceFromCenter.toFloat() / width) * maxFlip
            child.pivotX = (child.width / 2).toFloat()
            child.pivotY = (child.height / 2).toFloat()
            child.rotationX = flip
        }
    }
}