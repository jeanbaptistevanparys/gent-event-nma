package com.example.gentevent.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gentevent.model.Event
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top


@Composable
fun DetailEventScreen(navController: NavController?, viewModel: EventViewModel) {
    val event = viewModel.getEventClicked()
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
                    DetailEvent(event)
                }
            )
        }
    )
}

@Composable
fun DetailEvent(event: Event?) {
    Column {
    Text(text = "Detail Event")
        event?.title?.let { Text(text = it) }
    }


}

@Preview
@Composable
fun PreviewDetailEvent() {
    DetailEvent(Event("Maandag", 1,1,"","","","",""))
    //todo: fix preview
}