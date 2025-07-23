package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.androidpracticumcustomview.pxToDp


/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
    modifier: Modifier = Modifier,
    fadeInAnimationDuration: Int = 2000,
    translateAnimationDuration: Int = 5000,
    firstChild: @Composable (() -> Unit)?,
    secondChild: @Composable (() -> Unit)?,
) {
    // Блок создания и инициализации переменных
    // ..
    var visible by remember { mutableStateOf(false) }
    var firstChildIntSizePx by remember { mutableStateOf(IntSize(0,0)) }
    var secondChildIntSizePx by remember { mutableStateOf(IntSize(0,0)) }

    // Блок активации анимации при первом запуске
    LaunchedEffect(Unit) { visible = true }

    // Основной контейнер
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val halfHeight = maxHeight / 2
        val firstChildAnimatedOffset by animateDpAsState(
            targetValue = if (visible) halfHeight - (firstChildIntSizePx.height/2).pxToDp().dp else 0.dp,
            animationSpec = tween(durationMillis = translateAnimationDuration)
        )
        val secondChildAnimatedOffset by animateDpAsState(
            targetValue = if (visible) halfHeight - (secondChildIntSizePx.height/2).pxToDp().dp else 0.dp,
            animationSpec = tween(durationMillis = translateAnimationDuration)
        )
        val animatedAlpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = tween(durationMillis = fadeInAnimationDuration)
        )
        CustomBox(modifier = Modifier.alpha(animatedAlpha)) {
            Box(
                modifier = Modifier
                    .onSizeChanged { firstChildIntSizePx = it }
                    .offset(y = -firstChildAnimatedOffset)
            ) {
                firstChild?.invoke()
            }
            Box(
                modifier = Modifier
                    .onSizeChanged { secondChildIntSizePx = it }
                    .offset(y = secondChildAnimatedOffset)
            ) {
                secondChild?.invoke()
            }
        }
    }
}

@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier,
    ) { measurables, constraints ->
        require(measurables.size <= 2) {
            "CustomBox can have at most 2 children"
        }

        val placeableOne = measurables[0].measure(constraints)
        val placeableTwo = measurables[1].measure(constraints)

        layout(constraints.maxWidth, constraints.maxHeight) {
            val firstCenterX = (constraints.maxWidth - placeableOne.width)/2
            val firstCenterY = (constraints.maxHeight - placeableOne.height)/2
            val secondCenterX = (constraints.maxWidth - placeableTwo.width)/2
            val secondCenterY = (constraints.maxHeight - placeableTwo.height)/2
            placeableOne.placeRelative(
                x = firstCenterX,
                y = firstCenterY,
            )
            placeableTwo.placeRelative(
                x = secondCenterX,
                y = secondCenterY,
            )
        }
    }
}

@Composable
@Preview
fun CustomContainerComposePreview() {
    CustomContainerCompose(
        firstChild = {
            Text(
                text = "ChildView Top",
                color = Color.Black,
            )
        },
        secondChild = {
            Text(
                text = "ChildView Bottom",
                color = Color.Black,
            )
        }
    )
}