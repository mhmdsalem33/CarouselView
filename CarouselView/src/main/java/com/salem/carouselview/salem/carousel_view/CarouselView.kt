package com.salem.carouselview.salem.carousel_view


import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import androidx.annotation.IntDef
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.salem.carouselview.salem.listener.CarouselPositionListener


/**
 *  Mohamed Salem
 *  17/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

class CarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    private var isAutoScrollEnabled: Boolean = false
    private var autoScrollDelayMillis: Long = 3000
    private var isLooping: Boolean = true
    private var scrollHandler: Handler? = null
    private var scrollRunnable: Runnable? = null
    private var positionListener: com.salem.carouselview.salem.listener.CarouselPositionListener? = null
    private var onScrollListener: OnScrollListener? = null // For removing listener later

    private val autoScrollHandler = Handler(Looper.getMainLooper())
    private var currentItemPosition: Int = RecyclerView.NO_POSITION

    private var recyclerViewScrollType: Boolean? = null

    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            if (isAutoScrollEnabled) {
                scrollToNextItem()
                autoScrollHandler.postDelayed(this, autoScrollDelayMillis)
            }
        }
    }

    // Scroll to the next item and loop back to the first one when reaching the end
    private fun scrollToNextItem() {
        if (adapter == null || adapter!!.itemCount == 0) return

        val layoutManager = layoutManager as LinearLayoutManager
        val nextPosition = if (currentItemPosition == adapter!!.itemCount - 1 && isLooping) {
            0  // Loop back to the first item
        } else {
            currentItemPosition + 1  // Move to the next item
        }


        if (recyclerViewScrollType == true) {
            smoothScrollToPosition(nextPosition)
        } else if (recyclerViewScrollType == false) {
            scrollToPosition(nextPosition)
        } else {
            smoothScrollToPosition(nextPosition)
        }

    }



    init {
        setHasFixedSize(true)
        itemAnimator = null
    }

    // for enable smoothScrollToPosition
    fun enableSmoothScrollToPositionWithAutoScroll() {
        recyclerViewScrollType = true
    }

    // for enable scrollToPosition
    fun disableSmoothScrollToPositionWithAutoScroll() {
        recyclerViewScrollType = false
    }


    fun startAutoScroll() {
        if (!isAutoScrollEnabled) {
            isAutoScrollEnabled = true
            autoScrollHandler.postDelayed(autoScrollRunnable, autoScrollDelayMillis)
        }
    }

    // Function to stop auto-scrolling
    fun stopAutoScroll() {
        isAutoScrollEnabled = false
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }



    fun setSmoothScrollToPosition(position: Int) {
        this.postDelayed({
            this.smoothScrollToPosition(position)
        }, 200)
    }

    fun setScrollToPosition(position: Int) {
        this.postDelayed({
            this.scrollToPosition(position)
        }, 200)
    }


    fun scrollItemToCenter(position: Int) {
        if (adapter == null || adapter!!.itemCount == 0 || position < 0 || position >= adapter!!.itemCount) {
            return
        }

        // First, scroll to the position smoothly
        smoothScrollToPosition(position)

        // After the smooth scroll, we can make sure the item is centered by posting a delayed task
        postDelayed({
            val view = findViewHolderForAdapterPosition(position)?.itemView
            view?.let {
                val snapHelper =
                    LinearSnapHelper()  // You could use FastLinearSnapHelper if desired
                val layoutManager = layoutManager
                val snapDistance = snapHelper.calculateDistanceToFinalSnap(layoutManager!!, view)
                if (snapDistance != null) {
                    smoothScrollBy(snapDistance[0], snapDistance[1])  // Ensure the view is centered
                }
            }
        }, 200)
    }



    // init linear snap this code by mohamed salem
    fun initLinearSnapHelper() {
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(this)
    }

    // init other snap helper or custom your own snap helper
    fun initSnapHelper(snapHelper: SnapHelper) {
        snapHelper.attachToRecyclerView(this)
    }


    // Add the position listener
    fun addCarouselPositionListener(listener: com.salem.carouselview.salem.listener.CarouselPositionListener) {
        this.positionListener = listener

        var previousPosition = NO_POSITION

        // Define the onScrollListener to detect position changes
        onScrollListener = object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Get the current visible item position
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstCompletelyVisibleItemPosition()

                // Only notify listener if the position has changed
                if (currentPosition != NO_POSITION && currentPosition != previousPosition) {
                    currentItemPosition = currentPosition

                    previousPosition = currentPosition
                    positionListener?.onPositionChanged(currentPosition)
                }
            }
        }

        // Attach the listener to the RecyclerView
        this.addOnScrollListener(onScrollListener!!)
    }


    /*
        fun get the current index and selected position but you must enable the addCarouselPositionListener
        in your fragment , activity to get the current selected item position
     */
    fun getCurrentPosition() = currentItemPosition


    // Remove the position listener to prevent memory leaks
    fun removeCarouselPositionListener() {
        this.positionListener = null
        onScrollListener?.let { this.removeOnScrollListener(it) }
        onScrollListener = null
    }

    fun enableLooping(isLooping: Boolean) {
        this.isLooping = isLooping
    }

    fun setOrientation(@Orientation orientation: Int) {
        layoutManager = LinearLayoutManager(context, orientation, false)
    }




    @IntDef(HORIZONTAL, VERTICAL)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Orientation

    companion object {
        const val HORIZONTAL = LinearLayoutManager.HORIZONTAL
        const val VERTICAL = LinearLayoutManager.VERTICAL
    }
}
