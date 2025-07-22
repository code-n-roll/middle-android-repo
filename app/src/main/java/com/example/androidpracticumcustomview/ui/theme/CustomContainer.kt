package com.example.androidpracticumcustomview.ui.theme

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isNotEmpty

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */

class CustomContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        setWillNotDraw(false)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val parentWidth = measuredWidth
        val parentHeight = measuredHeight
        val parentPaddingLeft = paddingLeft
        val parentPaddingRight = paddingRight
        val parentPaddingTop = paddingTop
        val parentPaddingBottom = paddingBottom
        val contentWidth = parentWidth - parentPaddingLeft - parentPaddingRight

        if (isNotEmpty()) {
            val firstChild = getChildAt(0)
            if (!firstChild.isGone) {
                val childWidth = firstChild.measuredWidth
                val childHeight = firstChild.measuredHeight

                // center by horizontal
                val childLeft = parentPaddingLeft + (contentWidth - childWidth) / 2
                // top
                val childTop = parentPaddingTop

                firstChild.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight)
            }
        }

        if (childCount > 1) {
            val secondChild = getChildAt(1)
            if (!secondChild.isGone) {
                val childWidth = secondChild.measuredWidth
                val childHeight = secondChild.measuredHeight

                // center horizontally
                val childLeft = parentPaddingLeft + (contentWidth - childWidth) / 2
                // bottom
                val childBottom = parentPaddingTop + parentHeight + parentPaddingBottom

                secondChild.layout(childLeft, childBottom - childHeight, childLeft + childWidth, childBottom)
            }
        }
    }

    override fun addView(child: View) {
        if (childCount > MAX_CHILDREN) {
            throw IllegalStateException("Maximum number of children 2 is reached")
        }
        super.addView(child)
    }

    companion object {
        private const val MAX_CHILDREN = 2
    }
}