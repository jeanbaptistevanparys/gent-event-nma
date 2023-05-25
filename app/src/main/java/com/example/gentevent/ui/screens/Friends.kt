package com.example.gentevent.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.gentevent.ui.screens.components.RoundedContainer
import com.example.gentevent.ui.screens.components.Top
import com.example.gentevent.ui.theme.GenteventTheme

@Composable
fun FriendsScreen(navController: NavHostController?) {
    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colors.background),
        topBar = {
            Top {
                navController?.popBackStack()
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
                    FriendSearchBar()
                    FriendRequests(mapOf("Bob" to "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj"))
                    Friends(mapOf("Bob" to "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj"))
                }

            }
        }
    )

}

@Composable
fun FriendSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black.copy(alpha = 0.33f))
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = TextFieldValue(),
        onValueChange = {},
        placeholder = { Text(text = "🔍 Search friends") },
    )
}

@Composable
fun FriendRequests(friends: Map<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Friend requests")
        friends.forEach {
            FriendRow(it.key, it.value) {
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "accept",
                            tint = Color.Gray,
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "decline",
                            tint = Color.Gray,
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun Friends(friends: Map<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Friends")
        friends.forEach {
            FriendRow(it.key, it.value) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "delete",
                        tint = Color.Gray
                    )
                }
            }
        }
    }

}

@Composable
fun FriendRow(name: String, src: String, content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Image(
            painter = rememberAsyncImagePainter(model = src),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)                       // clip to the circle shape
        )
        Text(text = name)
        content()
    }
}


@Preview
@Composable
fun FriendsScreenPreview() {
    GenteventTheme {
        FriendsScreen(null)
    }
}