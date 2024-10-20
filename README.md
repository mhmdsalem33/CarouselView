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
