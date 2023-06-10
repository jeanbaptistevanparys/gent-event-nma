package com.example.gentevent.ui.screens.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun FriendsScreen(navController: NavHostController?, viewModel: FriendsViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val onclick = { navController?.navigate("detail_friends") }
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
                    FriendSearchBar(viewModel)
                    if (uiState.searchedFriends.isNotEmpty()){
                        Friends(uiState.searchedFriends, viewModel, onclick)
                    }
                    else{
                        if (uiState.friendRequests.isNotEmpty())
                            FriendRequests(uiState.friendRequests, viewModel, {})

                        if (uiState.friends.isNotEmpty())
                            Friends(uiState.friends, viewModel, onclick)
                    }


                }

            }
        }
    )

}

@Composable
fun FriendSearchBar(viewModel: FriendsViewModel) {
    val search = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Black.copy(alpha = 0.33f))
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = search.value,
        onValueChange = {
            search.value = it
            viewModel.searchFrieds(it)
        },
        placeholder = { Text(text = "🔍 Search friends") },
    )
}

@Composable
fun FriendRequests( friends: List<Friend>, viewModel: FriendsViewModel, onclick: () -> Unit?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Friend requests")
        friends.forEach {
            FriendRow(viewModel,it, onclick) {
                Row {
                    IconButton(onClick = {
                        viewModel.addFriend(it)
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Check,
                            contentDescription = "accept",
                            tint = Color.Gray,
                        )
                    }
                    IconButton(onClick = {
                        viewModel.removeFriendRequest(it)
                    }) {
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
fun Friends(friends: List<Friend>, viewModel: FriendsViewModel, onclick: () -> Unit?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Friends")
        friends.forEach {
            FriendRow(viewModel,it , onclick) {
                IconButton(onClick = {
                    viewModel.removeFriend(it)
                }) {
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
fun FriendRow(viewModel: FriendsViewModel,friend : Friend, onclick: () -> Unit? , content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(10.dp)
            .clickable {
                viewModel.clickFriend(friend)
                onclick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        Image(
            painter = rememberAsyncImagePainter(model = friend.image),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Text(text = friend.name)
        content()
    }
}
