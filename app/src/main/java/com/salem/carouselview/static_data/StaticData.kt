package com.salem.carouselview.static_data

import com.salem.carouselview.carousel_model.CarouselModel

class StaticData {

    val carouselItems = mutableListOf(
        CarouselModel(imageUrl = "https://images.pexels.com/photos/2097090/pexels-photo-2097090.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/28618634/pexels-photo-28618634/free-photo-of-fresh-quinoa-and-vegetables-salad-in-rustic-bowl.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/12786340/pexels-photo-12786340.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/14686445/pexels-photo-14686445.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/13522852/pexels-photo-13522852.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/1109197/pexels-photo-1109197.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/7225621/pexels-photo-7225621.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/7225242/pexels-photo-7225242.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/15298781/pexels-photo-15298781/free-photo-of-ramen-in-bowl.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
        CarouselModel(imageUrl = "https://images.pexels.com/photos/19386650/pexels-photo-19386650/free-photo-of-food-served-on-a-table.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"),
    )


   private val imageList = mutableListOf(
        "https://images.pexels.com/photos/28889501/pexels-photo-28889501/free-photo-of-roasted-vegetables-and-beans-on-yogurt-plate.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        "https://images.pexels.com/photos/13887561/pexels-photo-13887561.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
        "https://images.pexels.com/photos/13887560/pexels-photo-13887560.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/9558174/pexels-photo-9558174.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/22744176/pexels-photo-22744176/free-photo-of-a-person-eating-cereal.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
        "https://images.pexels.com/photos/9558172/pexels-photo-9558172.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load",
    )

    val carouselItemsTwo = imageList.map { CarouselModel(it) }


    val layoutManagerListName = mutableListOf(
        "Ms Layout Manager" ,
        "Center Layout Manager" ,
        "Basic Carousel" ,
        "Center Scale" ,
        "Depth Carousel" ,
        "Depth Carousel +",
        "Fade Carousel" ,
        "Flip Carousel" ,
        "Rotate 3D Carousel" ,
        "Skew Carousel" ,
        "Smooth Zoom Carousel" ,
        "Smooth Upward Carousel" ,
        "Tilt Carousel" ,
        )

}