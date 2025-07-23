package com.example.androidpracticumcustomview

import android.content.res.Resources

/**
 * Converts a pixel value to its equivalent in dp.
 */
fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()