package com.example.gentevent.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedContainer(
    innerPadding: PaddingValues,
    content: @Composable () -> Unit) {
Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
    ) {
        content()
    }
}