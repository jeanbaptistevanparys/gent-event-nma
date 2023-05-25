package com.example.gentevent.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gentevent.R

@Composable
fun Top(OnBack: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)

        ) {
        Box(
            modifier = Modifier
            .fillMaxSize(),
        ) {
            IconButton(
                onClick = {
                            OnBack()
                },
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterStart),
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "settings",
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.genteventlogo),
                contentDescription = "gent event logo",
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }
    }

}


