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


