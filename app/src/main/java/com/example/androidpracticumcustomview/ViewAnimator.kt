package com.example.androidpracticumcustomview

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import com.example.androidpracticumcustomview.ui.theme.CustomContainer

object ViewAnimator {

    fun animateChild(
        container: CustomContainer,
        view: View,
        fadeInDuration: Long = 2000L,
        translateDuration: Long = 5000L,
    ) {
        view.visibility = View.VISIBLE

        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = fadeInDuration
        }

        val containerCenterY = container.height / 2f
        val finalY = view.y
        val startY = containerCenterY - finalY

        val translate = TranslateAnimation(0f, 0f, startY, 0f).apply {
            duration = translateDuration
        }

        val animationSet = AnimationSet(true).apply {
            addAnimation(fadeIn)
            addAnimation(translate)
        }

        view.startAnimation(animationSet)
    }
}