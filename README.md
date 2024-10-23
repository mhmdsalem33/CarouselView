# CarouselView Library

CarouselView is a customizable Android library that provides a simple and efficient way to implement a carousel-like view using RecyclerView. It supports smooth scrolling, automatic scrolling, and snapping to ensure a polished user experience.

## Features

- **Auto-Scrolling**: Automatically scroll through items with a configurable delay.
- **Smooth Scrolling**: Option to enable or disable smooth scrolling to the next item.
- **Looping Support**: Configure whether the carousel should loop back to the beginning after reaching the end.
- **Snap Helper Integration**: Easily integrate LinearSnapHelper or custom snap helpers for item alignment.
- **Position Listener**: Callback to track the currently visible item position.
- **Dynamic Item Management**: Easily add, remove, or replace items in the carousel.

## Installation

To install and use Android Carousel View Library, follow these step-by-step instructions:
### Step 1:  Add jitpack in **settings.gradle.kts**

```jsx

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io" ) }

    }
}
```


2. Add this dependency in **build.gradle** :

```jsx

dependencies {
    // Replace 'latest-version' with the actual latest version number
    implementation("com.github.mhmdsalem33:CarouselView:latest-version")
}
```

3. **Create your custom data class :

```jsx

data class CarouselModel( val imageUrl : String )

```


4. **Add a carousel view to your xml file :

```jsx

<com.salem.carouselview.salem.carousel_view.CarouselView
    android:id="@+id/carouselRecyclerView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginBottom="5dp"
    android:clipChildren="false"
    android:clipToPadding="false"
    android:orientation="horizontal"
    android:overScrollMode="never"
    android:paddingHorizontal="10dp"
    android:paddingTop="50dp"
    tools:listitem="@layout/item_carousel”
    app:layoutManager=".salem.carousel_layout_manager.CenterLayoutManager"
    />

```

5. ** Create your item_carousel layout file :

```jsx

 <?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="292.5dp"
    android:layout_height="550dp"
    xmlns:tools="http://schemas.android.com/tools"
    app:cardCornerRadius="50dp"
    app:cardElevation="0dp"
    android:layout_marginHorizontal="2dp">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <ImageView
            android:id="@+id/cover"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:contentDescription="@null"
            android:scaleType="centerCrop"
            tools:src="@drawable/fried_chicken"
            android:visibility="visible" />

        <ImageView
            android:id="@+id/remove_item"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:contentDescription="@null"
            android:padding="20dp"
            android:src="@drawable/clear"
            android:visibility="visible"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:tint="@color/white" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</androidx.cardview.widget.CardView>



```

5. **Create Your Data**
To display items in the carousel, you can create a data class and populate it with image URLs. Below is an example of some fake data to help you get started:

```jsx
class StaticData {

    val carouselItems = mutableListOf(
        CarouselModel(imageUrl = "https://images.pexels.com/photos/18908094/pexels-photo-18908094/free-photo-of-pile-on-pancakes-with-sugar-powder-chocolate-and-fruits.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/16386492/pexels-photo-16386492/free-photo-of-chocolate-cake-with-strawberries.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/14686445/pexels-photo-14686445.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/13522852/pexels-photo-13522852.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/1109197/pexels-photo-1109197.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/7225621/pexels-photo-7225621.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/7225242/pexels-photo-7225242.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/15298781/pexels-photo-15298781/free-photo-of-ramen-in-bowl.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/2641886/pexels-photo-2641886.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
    )


   private val imageList = mutableListOf(
        "https://images.pexels.com/photos/16877270/pexels-photo-16877270/free-photo-of-close-up-of-a-burger-with-a-lot-of-melted-cheese.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/8882936/pexels-photo-8882936.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/8880730/pexels-photo-8880730.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/12944635/pexels-photo-12944635.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/11354334/pexels-photo-11354334.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/6205798/pexels-photo-6205798.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
    )

    val carouselItemsTwo = imageList.map { CarouselModel(it) }



}


```

6. **Create an Adapter for the Carousel View Library** You can create a custom adapter for the carousel view library, which can also accept list adapters. Below is an example to help you get started:

```jsx
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
        holder.itemView.onSingleClick {
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
     //   notifyItemInserted(items.size - 1) // Only notify the newly added item
        notifyDataSetChanged() // Notify the adapter of changes
    }

    // Function to add a single item with smooth scrolling to the newly added position
    fun addItemWithSmoothScroll(newItem: CarouselModel) {
        items.add(newItem)
        recyclerView?.smoothScrollToPosition(items.size - 1)
       // notifyItemInserted(items.size - 1) // Only notify the newly added item
        notifyDataSetChanged() // Notify the adapter of changes

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
            items.removeAt(position)
            notifyItemRemoved(position)
            Log.e("testItems" , items.toString())
        }
    }

    // Function to remove all items
    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }


}



```


7. ** setup your activity or fragment** Here is example for activity

```jsx
class MainActivity : AppCompatActivity(), CarouselPositionListener {

    private lateinit var binding: ActivityMainBinding
    private val mainTag = "MainActivity"

    private val carouselView by lazy { binding.carouselRecyclerView }  // 1-  init your carouselRecyclerView
    private val staticData by lazy { StaticData() } //  2- class for our static data
    private val carouselAdapter by lazy { CarouselAdapter() } // 3- our custom adapter you can make your own adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        // 4- init the position listener with your fragment or activity to get the current selected position
        carouselView.addCarouselPositionListener(this)


        // 5- add your custom adapter
        carouselView.adapter = carouselAdapter


        // 6- set carousel view orientation
        carouselView.setOrientation(CarouselView.HORIZONTAL)

        // 7- add your data to your adapter
        carouselAdapter.updateItems(staticData.carouselItems)


        // 8 - optional init  linear snap helper to become items in center automatic
        //       binding.carouselRecyclerView.initLinearSnapHelper()

        // 9 init layout manager
        initMsLayoutManager()
    }

    private fun initMsLayoutManager() {
        val msLayoutManager =
            MsCarouselLayoutManager(
                context = this,
                orientation = RecyclerView.HORIZONTAL,
                reverseLayout = false
            )
        msLayoutManager.scaleView(true)
        binding.carouselRecyclerView.layoutManager = msLayoutManager
    }


    override fun onPositionChanged(currentPosition: Int) {
        Log.e(mainTag, currentPosition.toString())
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.carouselRecyclerView.removeCarouselPositionListener() // to prevent memory leak

    }

}



```


8. ** Here is a lot of carousel layout manager you can use to make your own purview choose your favorite one. 
```jsx
 private fun initMsLayoutManager() {
    val msLayoutManager =
        MsCarouselLayoutManager(
            context = this,
            orientation = RecyclerView.HORIZONTAL,
            reverseLayout = false
        )
    msLayoutManager.scaleView(true)
    binding.carouselRecyclerView.layoutManager = msLayoutManager
}


private fun initMsCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        MsCarouselLayoutManager(
            this,
            orientation = RecyclerView.HORIZONTAL,
            false,
            shrinkAmount = 0.15f, // change shrink as you need
            shrinkDistance = 0.9f // change distance as you need
        )
}

private fun initCenterLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        CenterLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
}

private fun initCenterScaleLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        CenterScaleLayoutManager(this)
}

private fun initBasicCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        BasicCarouselLayoutManager(this)
}

private fun initDepthCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
       DepthCarouselLayoutManager(this)
}

private fun initDepthCarouselLayoutManagerPlus() {
    binding.carouselRecyclerView.layoutManager =
        DepthCarouselLayoutManagerPlus(
            this,
            0.5f
        )
}


private fun initFadeCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        FadeCarouselLayoutManager(
            this,
            scaleFactor = 0.2f,
            fadeFactor = 0.5f
        )
}

private fun initFlipCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        FlipCarouselLayoutManager(
            this,
            maxFlip = 50f
        )
}


private fun initRotate3DCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        Rotate3DCarouselLayoutManager(
            this,
            30f
        )
}

private fun initSkewCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        SkewCarouselLayoutManager(
            this,
            0.5f
        )
}

private fun initSmoothUpwardCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        SmoothUpwardCarouselLayoutManager(
            this,
            30f
        )
}


private fun initSmoothZoomLayoutManager() {
    val smoothLayoutManager =
        SmoothZoomLayoutManager(
            this,
            orientation = RecyclerView.HORIZONTAL,
            false
        )
    smoothLayoutManager.apply {
        setScaleView(true)
        setScrollSpeed(15f)
        setShrinkAmounts(0.15f)
        setShrinkDistance(1f)
    }
    binding.carouselRecyclerView.layoutManager = smoothLayoutManager

}

private fun initTiltCarouselLayoutManager() {
    val tiltCarouselLayoutManager = TiltCarouselLayoutManager(
        this
    ).apply { setTiltFactor(15f) }
    binding.carouselRecyclerView.layoutManager = tiltCarouselLayoutManager
}

private fun initWaveCarouselLayoutManager() {
    binding.carouselRecyclerView.layoutManager =
        WaveCarouselLayoutManager(
            this,
            RecyclerView.HORIZONTAL,
            false
        )
}



```


```jsx
Contact Me
I’d love to hear from you! Feel free to reach out to me via email at:

📧 Email: mhmd.salem33@yahoo.com
🔗 LinkedIn: https://www.linkedin.com/in/mhmd-salem-a004a0213/

Let’s connect and explore opportunities together!
```


## License

[![License](https://img.shields.io/static/v1?label=Licence&message=MIT&color=blue)](https://opensource.org/license/MIT)




