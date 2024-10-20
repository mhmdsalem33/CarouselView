package com.salem.carouselview.static_data

import com.salem.carouselview.carousel_model.CarouselModel

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