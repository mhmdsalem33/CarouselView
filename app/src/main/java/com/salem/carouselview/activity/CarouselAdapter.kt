package com.salem.carouselview.activity

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.salem.carouselview.R
import com.salem.carouselview.carousel_model.CarouselModel
import com.salem.carouselview.databinding.CarouselItemBinding
import com.salem.carouselview.extentions.OnSingleClick.onSingleClick
import com.salem.carouselview.extentions.loadImage

class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    var onItemClick: ((item : CarouselModel, position: Int) -> Unit)? = null
    private var items = mutableListOf<CarouselModel>()
    private var recyclerView: RecyclerView? = null

    // Attach the RecyclerView instance for scroll handling
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    // ViewHolder class for binding
    inner class CarouselViewHolder( val binding: CarouselItemBinding ) : RecyclerView.ViewHolder(binding.root)

    // Inflating the ViewHolder with the layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = CarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    // Binding data to the ViewHolder
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val item = items[position]

        holder.binding.cover.loadImage(item.imageUrl) // Load the image

        // Set click for item
        holder.itemView.setOnClickListener {
            onItemClick?.invoke( item ,  position)
        }

        // Set click for remove button
        holder.binding.removeItem.setOnClickListener {
            removeItem(item)
        }

    }

    // Returning the total number of items
    override fun getItemCount(): Int = items.size


    fun replaceAllItems(newItems: List<CarouselModel>) {
        this.items.clear() // Clear existing items
        this.items.addAll(newItems) // Add new items
        notifyDataSetChanged() // Notify the adapter of changes
    }

    fun addNewItemsWithAnimation(newItems: MutableList<CarouselModel>) {
        recyclerView?.scrollToPosition(items.size) // Scroll to the last position
        this.items.addAll(newItems) // Add new items
        notifyDataSetChanged() // Notify the adapter of the changes

        // Trigger the layout animation
        recyclerView?.layoutAnimation = AnimationUtils.loadLayoutAnimation(recyclerView?.context, R.anim.layout_animation)
        recyclerView?.scheduleLayoutAnimation()
    }


    // Function to update the entire list and scroll to the last position smoothly
    fun updateItems(newItems: List<CarouselModel>) {
        recyclerView?.smoothScrollToPosition(items.size)
        items.addAll(newItems)
        notifyDataSetChanged()
    }



    // Function to add a single item with scrolling to the newly added position
    fun addItem(newItem: CarouselModel) {
        items.add(newItem)
        recyclerView?.scrollToPosition(items.size - 1)
//        notifyItemInserted(items.size - 1) // Only notify the newly added item
        notifyDataSetChanged()

    }

    // Function to add a single item with smooth scrolling to the newly added position
    fun addItemWithSmoothScroll(newItem: CarouselModel) {
        items.add(newItem)
        recyclerView?.smoothScrollToPosition(items.size - 1)
//        notifyItemInserted(items.size - 1) // Only notify the newly added item
        notifyDataSetChanged()

    }


    // Function to replace all current items with new list
    fun replaceItems(newItems: List<CarouselModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    // Function to remove a specific item
    fun removeItem(item: CarouselModel) {
        val position = items.indexOf(item)
        if (position >= 0) {
            items.remove(item)
            notifyDataSetChanged()
            Log.e("testItems" , items.toString())
        }
    }

    // Function to remove all items
    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }


}
