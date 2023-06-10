package com.example.gentevent.ui.screens.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top

@Composable
fun DetailFriendsScreen(navController: NavHostController, viewModel: FriendsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {
            Top {
                navController.popBackStack()
            }
        },
        content = { innerPadding ->
            RoundedContainer(
                innerPadding = innerPadding,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = uiState.clickedFriend?.image),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .align(alignment = Alignment.CenterHorizontally)

                    )
                    uiState.clickedFriend?.let {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(alignment = Alignment.CenterHorizontally),

                        )
                        Text(
                            text = it.description,
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            )
                        Text(
                            text = "went to ${it.events} events",
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                        )
                        Button(
                            onClick = {
                                viewModel.removeFriend(it)
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .height(50.dp)
                                .width(200.dp)
                                .align(alignment = Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Remove Friend")
                        }

                    }
                }

            }
        }
    )
}
