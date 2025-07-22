package com.example.androidpracticumcustomview

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.view.doOnLayout
import com.example.androidpracticumcustomview.ui.theme.CustomContainer


class XmlActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startXmlPracticum()
    }

    private fun startXmlPracticum() {
        val customContainer = CustomContainer(this)

        setContentView(customContainer)

        customContainer.setOnClickListener {
            finish()
        }

        val firstView = TextView(this).apply {
            text = "Child View Top"
            textSize = 24f
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
        }

        val secondView = TextView(this).apply {
            text = "Child View Bottom"
            textSize = 24f
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
            )
        }

        setupAndAnimateViews(
            customContainer = customContainer,
            view1 = firstView,
            view2 = secondView
        )
    }

    private fun setupAndAnimateViews(customContainer: CustomContainer, view1: View, view2: View) {
        customContainer.addView(view1)
        customContainer.addView(view2)

        customContainer.doOnLayout {
            ViewAnimator.animateChild(customContainer, view1)
            ViewAnimator.animateChild(customContainer, view2)
        }
    }
}