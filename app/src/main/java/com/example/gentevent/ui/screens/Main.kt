package com.example.gentevent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gentevent.R
import com.example.gentevent.ui.theme.GenteventTheme


@Composable
fun MainScreen(navController: NavHostController?) {
    Scaffold(topBar = { MainTop(navController) }, content = { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            DateRow()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),

                content = {
                    item {
                        EventCard("https://visit.gent.be/sites/default/files/styles/photo_large/public/img/poi/hero/PuurGent-DT007776.JPG?itok=ovwPh5rN", "moeder jager", "the place", 4 , "12/05" )
                    }
                    item {
                        EventCard("https://i1.sndcdn.com/artworks-LvSA99OTfi9LqsYA-vd9FQw-t500x500.jpg","moeder jager", "the place", 2,"13/05" )
                    }
                })
        }
    })
}

@Composable
fun MainTop(navController: NavHostController?) {
    TopAppBar(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .height(200.dp),

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                IconButton(
                    onClick = {
                        navController?.navigate("settings")
                    },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "settings",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {
                    navController?.navigate("friends")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.friends),
                        contentDescription = "friends",
                        tint = Color.White
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.genteventlogo),
                contentDescription = "gent event logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
            )
        }

    }
}

@Composable
fun DateRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, top = 0.dp),
        verticalAlignment = Alignment.Top,

        ) {
        item {
            DateButton("MA")
        }
        item {
            DateButton("DI")
        }
        item {
            DateButton("WO")
        }
        item {
            DateButton("DO")
        }
        item {
            DateButton("VR")
        }
    }

}

@Composable
fun DateButton(s: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(10.dp)
            .height(50.dp)
            .width(80.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.White
        )
    ) {
        Text(text = s)
    }
}

@Composable
fun EventCard( src: String, title: String = "Event", location: String = "Location", friends: Int = 2 , time: String = "Time") {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 10.dp,
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = src),
                contentDescription = "event image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column (
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = title, style = MaterialTheme.typography.h5)
                    Row(
                        modifier = Modifier
                            .clickable { /*TODO*/ },
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "location",
                            modifier = Modifier
                                .size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = location, style = MaterialTheme.typography.h6)
                    }
                    Text(text = "$friends friends are going", style = MaterialTheme.typography.h6)
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    Text(
                        text = time,
                        color = Color.White,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun MaintPreview() {
    GenteventTheme {
        MainScreen(null)
    }
}
