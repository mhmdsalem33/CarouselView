package com.salem.carouselview.extentions

import android.content.Context

/**
 *  Mohamed Salem
 *  18/10/2024
 *  https://www.linkedin.com/in/mhmd-salem-a004a0213/
 */

fun Float.dpToPx(context: Context): Float {
    return this * context.resources.displayMetrics.density
}
