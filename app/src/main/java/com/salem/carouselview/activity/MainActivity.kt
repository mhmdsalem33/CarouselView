package com.salem.carouselview.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.salem.carouselview.carousel_model.CarouselModel
import com.salem.carouselview.databinding.ActivityMainBinding
import com.salem.carouselview.salem.carousel_layout_manager.BasicCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.CenterLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.CenterScaleLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.DepthCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.DepthCarouselLayoutManagerPlus
import com.salem.carouselview.salem.carousel_layout_manager.FadeCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.FlipCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.MsCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.Rotate3DCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.SkewCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.SmoothUpwardCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.SmoothZoomLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.TiltCarouselLayoutManager
import com.salem.carouselview.salem.carousel_layout_manager.WaveCarouselLayoutManager
import com.salem.carouselview.salem.carousel_view.CarouselView
import com.salem.carouselview.salem.listener.CarouselPositionListener
import com.salem.carouselview.static_data.StaticData

/**
 *  Mohamed Salem
 *  17/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */




class MainActivity : AppCompatActivity() , CarouselPositionListener {

    private lateinit var binding: ActivityMainBinding
    private val mainTag = "MainActivity"

    private val carouselView by lazy { binding.carouselRecyclerView }  // 1-  init your carouselRecyclerView
    private val staticData by lazy { StaticData() } //  2- class for our static data
    private val carouselAdapter by lazy { CarouselAdapter() } // 3- our custom adapter you can make your own adapter


    private val chooseLayoutManagerAdapter by lazy { ChooseLayoutManagerAdapter() }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        // 4- init the position listener with your fragment or activity to get the current selected position
        carouselView.addCarouselPositionListener(this)


        // 5- add your custom adapter
        carouselView.adapter = carouselAdapter



        // 6- set carousel view orientation
        carouselView.setOrientation( CarouselView.HORIZONTAL )

        // 7- add your data to your adapter
        carouselAdapter.updateItems( staticData.carouselItems )

//
       // 8 - optional init  linear snap helper to become items in center automatic
//             binding.carouselRecyclerView.initLinearSnapHelper()

//
        // 9 init layout manager
        initMsLayoutManager()

        // init the layout manager you can choose between layout manager there is a lot
        //Choose your layout manager just call the layout fun



        // select index 1 to be in center
//        binding.carouselRecyclerView.scrollItemToCenter(4 )


//        // direct scroll to position
//        binding.carouselRecyclerView.setScrollToPosition(4)
//

        // smooth scroll to position
//        binding.carouselRecyclerView.setSmoothScrollToPosition(1)


        // scroll item to center
        // binding.carouselRecyclerView.scrollItemToCenter(1)


//        // On Carousel view Click
        onItemCarouselViewClick()
//

        // on add carousel item
        binding.addItem.setOnClickListener {
            addItemWithSmoothScroll(CarouselModel("https://images.pexels.com/photos/27359353/pexels-photo-27359353/free-photo-of-a-table-with-several-bowls-of-food-on-it.jpeg?auto=compress&cs=tinysrgb&w=800&lazy=load"))
        }

        // add items
        binding.addItems.setOnClickListener {
            carouselAdapter.updateItems(staticData.carouselItemsTwo)
        }

        // remove all items
        binding.removeItems.setOnClickListener {
            carouselAdapter.clearItems()
        }


//        // init rec view choose layout manager
        initRecViewChooseLayoutManager()
        setDataToChooseLayoutManagerAdapter()
        onItemChooseLayoutManagerClick()


//        binding.carouselRecyclerView.startAutoScroll()



    }


    private fun onItemCarouselViewClick() {
        carouselAdapter.onItemClick = { itemModel, position ->

            binding.carouselRecyclerView.setSmoothScrollToPosition(position)
            Log.e("testPosition" , position.toString())
        }
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
        val layoutManager = SmoothUpwardCarouselLayoutManager(this, moveUpFactor = 60f, scaleDownFactor = 0.2f, visibleItemCount = 3)
            .apply {
                setItemSpacing(20)
                setRecyclerViewPadding(binding.carouselRecyclerView)
            }
        binding.carouselRecyclerView.layoutManager = layoutManager


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
            setScrollSpeed(1f)
            setShrinkDistance(0.9f)
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


    private fun addItemWithSmoothScroll(carouselModel: CarouselModel) {
        carouselAdapter.addItemWithSmoothScroll(carouselModel)
    }


    private fun addItemWithOutSmoothScroll(carouselModel: CarouselModel) {
        carouselAdapter.addItem(carouselModel)
    }


    private fun initRecViewChooseLayoutManager() {
        binding.recViewChooseLayoutManager.apply {
            adapter = chooseLayoutManagerAdapter
            layoutManager =
                CenterLayoutManager(
                    this@MainActivity,
                    RecyclerView.HORIZONTAL,
                    false
                )
        }
    }

    private fun setDataToChooseLayoutManagerAdapter() {
        chooseLayoutManagerAdapter.addItems(staticData.layoutManagerListName)
    }

    private fun onItemChooseLayoutManagerClick() {
        chooseLayoutManagerAdapter.onItemClick = { carouseName ->
            resetRecyclerViewState()
            when (carouseName) {
                "Ms Layout Manager" -> {
                    initMsLayoutManager()
                }

                "Center Layout Manager" -> {
                    initCenterLayoutManager()
                }

                "Basic Carousel" -> {
                    initBasicCarouselLayoutManager()
                }

                "Center Scale" -> {
                    initCenterScaleLayoutManager()
                }

                "Depth Carousel" -> {
                    initDepthCarouselLayoutManager()
                }

                "Depth Carousel +" -> {
                    initDepthCarouselLayoutManagerPlus()
                }

                "Fade Carousel" -> {
                    initFadeCarouselLayoutManager()
                }

                "Flip Carousel" -> {
                    initFlipCarouselLayoutManager()
                }

                "Rotate 3D Carousel" -> {
                    initRotate3DCarouselLayoutManager()
                }

                "Skew Carousel" -> {
                    initSkewCarouselLayoutManager()
                }

                "Smooth Zoom Carousel" -> {
                    initSmoothZoomLayoutManager()
                }

                "Smooth Upward Carousel" -> {
                    initSmoothUpwardCarouselLayoutManager()
                }

                "Tilt Carousel" -> {
                    initTiltCarouselLayoutManager()
                }

            }
        }
    }


    private fun resetRecyclerViewState() {
        binding.carouselRecyclerView.apply {
            stopScroll()
            itemAnimator = null
            adapter = null
            layoutManager = null
//            binding.carouselRecyclerView.setScrollToPosition(binding.carouselRecyclerView.getCurrentPosition())
            adapter = carouselAdapter
        }
    }

    override fun onPositionChanged(currentPosition: Int) {
        Log.e(mainTag, currentPosition.toString())
    }


    override fun onDestroy() {
        super.onDestroy()
//        binding.carouselRecyclerView.stopAutoScroll()
        binding.carouselRecyclerView.removeCarouselPositionListener() // to prevent memory leak

    }

}


