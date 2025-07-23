package com.example.androidpracticumcustomview.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

/*
Задание:
Реализуйте необходимые компоненты.
*/

@Composable
fun MainScreen(closeActivity: () -> Unit = {}) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable { closeActivity.invoke() },
            contentAlignment = Alignment.Center,
        ) {

            CustomContainerCompose(
                modifier = Modifier.fillMaxSize(),
                firstChild = {
                    Text(
                        modifier = Modifier,
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
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}