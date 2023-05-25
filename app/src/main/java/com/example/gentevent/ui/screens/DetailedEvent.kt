package com.example.gentevent.ui.screens

import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.gentevent.R
import com.example.gentevent.model.Event
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top


@Composable
fun DetailEventScreen(
    navController: NavController?, viewModel: EventViewModel?
) {
    val event = viewModel?.getEventClicked()
    Scaffold(modifier = Modifier.background(color = MaterialTheme.colors.background), topBar = {
        Top {
            navController?.popBackStack()
        }
    }, content = { innerPadding ->
        RoundedContainer(innerPadding = innerPadding, content = {
            if (event != null) {
                DetailEvent(event = event)
            }
        })
    })
}

@Composable
fun DetailEvent(event: Event) {
    Box(Modifier.fillMaxSize()) {

        Column {
            Modifier
                .padding(10.dp)
                .fillMaxSize()
            Image(
                painter = rememberAsyncImagePainter(model = event.imgUrl),
                contentDescription = "event image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = event.title,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(10.dp)
                )
                Column {
                    Text(
                        text = event.date,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = event.date,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            Row(
                Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.category),
                    contentDescription = "location",
                    modifier = Modifier.size(20.dp),
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = event.category, style = MaterialTheme.typography.h6)
            }
            MapsLink(event = event)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = event.description,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = "${event.friends} friends are going",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(10.dp)
            )
        }
        AddtoCallendar(event = event, modifier =
        Modifier.align(Alignment.BottomCenter)
            .padding(bottom = 20.dp)
            .height(50.dp)
            .width(200.dp),
            )
    }


}

@Composable
fun MapsLink(event: Event) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(event.location)) }

        Row(
            Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable
                    { context.startActivity(intent) },
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "location",
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = event.locationName, style = MaterialTheme.typography.h6)
        }
}

@Composable
fun AddtoCallendar(event: Event, modifier: Modifier) {
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_INSERT)
    intent.setData(CalendarContract.Events.CONTENT_URI)
    intent.putExtra(CalendarContract.Events.TITLE, event.title)
    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, event.location)
    intent.putExtra(CalendarContract.Events.DESCRIPTION, event.description)
    //todo find a way to add the date

    intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)

    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, event.startTime)
    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, event.endTime)
    intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE)
    intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)



    Button(modifier = modifier,
        shape = RoundedCornerShape(30.dp),
        onClick = {
            context.startActivity(intent)
        }) {
        Text(text = "check in", color = Color.White, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun PreviewDetailEvent() {
    //DetailEventScreen(    )

}