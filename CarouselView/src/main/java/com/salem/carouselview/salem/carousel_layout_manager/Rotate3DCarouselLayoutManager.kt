package com.salem.carouselview.salem.carousel_layout_manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Rotate3DCarouselLayoutManager(
    context: Context,
    private val maxRotation :Float = 30f      // Max degree of rotation
) : LinearLayoutManager(context, HORIZONTAL, false) {


    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        apply3DRotationEffect()
        return scrolled
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        apply3DRotationEffect()
    }

    private fun apply3DRotationEffect() {
        val centerX = width / 2

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: continue
            val childCenterX = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2
            val distanceFromCenter = centerX - childCenterX

            // Rotate items based on distance from center
            val rotation = (distanceFromCenter.toFloat() / width) * maxRotation
            child.pivotX = if (distanceFromCenter > 0) 0f else child.width.toFloat()
            child.pivotY = (child.height / 2).toFloat()
            child.rotationY = rotation
        }
    }
}