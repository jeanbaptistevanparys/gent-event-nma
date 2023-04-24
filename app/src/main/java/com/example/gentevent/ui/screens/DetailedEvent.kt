package com.example.gentevent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top

@Composable
fun DetailEventScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = { Top {
            navController?.popBackStack()
        }
        },
        content = { innerPadding ->
            RoundedContainer(
                innerPadding = innerPadding,
                content = {
                    DetailEvent()
                }
            )
        }
    )
}

@Composable
fun DetailEvent() {


}