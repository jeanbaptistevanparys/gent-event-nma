package com.example.gentevent.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gentevent.R
import com.example.gentevent.model.Event
import com.example.gentevent.ui.theme.GenteventTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavHostController?, viewModel: EventViewModel) {
    Scaffold(topBar = { MainTop(navController, viewModel) }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White)
        ) {
            DateRow(viewModel)
            when(viewModel.events) {
                is IEventsUIstate.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
                is IEventsUIstate.Success -> {
                    val events = (viewModel.events as IEventsUIstate.Success).Events
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()

                    ) {
                        events.events.forEach()
                          { event: Event ->
                            item {
                                EventCard(
                                    navController,
                                    viewModel,
                                    event
                                )
                            }
                        }
                    }

                }
                is IEventsUIstate.Error -> {
                    Text(
                        text = "something went wrong :(",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun MainTop(navController: NavHostController?, viewModel: EventViewModel) {
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
                    .clickable { viewModel.getEvents() }

            )
        }

    }
}

@Composable
fun DateRow(viewModel: EventViewModel) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
            ),
        verticalAlignment = Alignment.Top,

        ) {

        item {
            DateButton("MA") {
                viewModel.filterEvents("ma")
            }
        }
        item {
            DateButton("DI") {
                viewModel.filterEvents("di")
            }
        }
        item {
            DateButton("WO") {
                viewModel.filterEvents("wo")
            }
        }
        item {
            DateButton("DO") {
                viewModel.filterEvents("do")
            }
        }
        item {
            DateButton("VR") {
                viewModel.filterEvents("vr")
            }
        }
    }

}

@Composable
fun DateButton(s: String, onclick: () -> Unit) {
    Button(
        onClick = {
            onclick()
        },
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventCard(navController: NavHostController?, viewModel: EventViewModel?, event: Event) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        contentColor = Color.Black,
        elevation = 10.dp,
        onClick = {
            println(event)
            viewModel?.setEventClicked(event)
            navController?.navigate("detail_event")
        }
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = event.imgUrl),
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
                    Text(text = event.title, style = MaterialTheme.typography.h5)
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.category),
                            contentDescription = "category",
                            modifier = Modifier
                                .size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = event.category, style = MaterialTheme.typography.h6)
                    }
                    Row(
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
                        Text(text = event.locationName, style = MaterialTheme.typography.h6)
                    }
                    Text(text = "${event.friends} friends are going", style = MaterialTheme.typography.h6)
                }
                Button(
                    onClick = { /*TODO*/  },
                    modifier = Modifier
                        .padding(10.dp)
                        .height(50.dp)
                        .width(80.dp),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    if (viewModel?.upcomingEventUIState?.collectAsState()?.value?.UpcomingEvents?.filter { it.id == event.id }?.size != 0) {
                        Icon(
                            painter = painterResource(id = R.drawable.callendar),
                            contentDescription = "checked",
                            modifier = Modifier
                                .size(20.dp),
                            tint = Color.White
                        )
                    } else {
                        Text(
                            text = LocalDate.parse(event.date, DateTimeFormatter.ISO_DATE)
                                .format(DateTimeFormatter.ofPattern("dd-MM")),
                            color = Color.White,
                        )
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun MainPreview() {
    GenteventTheme {
       // MainScreen(null)
        //todo fix preview
    }
}
