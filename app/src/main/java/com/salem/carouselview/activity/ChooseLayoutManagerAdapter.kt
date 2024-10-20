package com.salem.carouselview.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.salem.carouselview.R
import com.salem.carouselview.carousel_model.CarouselModel
import com.salem.carouselview.databinding.ItemChooseLayoutManagerBinding


class ChooseLayoutManagerAdapter :
    RecyclerView.Adapter<ChooseLayoutManagerAdapter.CarouselViewHolder>() {

    var onItemClick: ((carouselName: String) -> Unit)? = null

    private var items = mutableListOf<String>()
    private var recyclerView: RecyclerView? = null


    fun addItems(list: List<String>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    // Attach the RecyclerView instance for scroll handling
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    // ViewHolder class for binding
    inner class CarouselViewHolder(val binding: ItemChooseLayoutManagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Inflating the ViewHolder with the layout
    private var index = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemChooseLayoutManagerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarouselViewHolder(binding)
    }

    // Binding data to the ViewHolder
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {

        val carouselName = items[position]

        holder.binding.tvLayoutManagerName.text = carouselName

        holder.itemView.setOnClickListener {
            index = position
            onItemClick?.invoke(carouselName)
            recyclerView?.smoothScrollToPosition(position)
            notifyDataSetChanged()
        }


        if (index == position) {
            holder.binding.root.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.red_4
                )
            )
            holder.binding.tvLayoutManagerName.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )

        } else {
            holder.binding.root.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
            holder.binding.tvLayoutManagerName.setTextColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.black
                )
            )

        }

    }

    // Returning the total number of items
    override fun getItemCount(): Int = items.size


}
